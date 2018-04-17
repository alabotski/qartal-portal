package com.nomis.rabbit.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author Alexander Sokolov
 */
public class TaskStatusManagerImpl implements TaskStatusManager {

  private static final Logger logger = LogManager.getLogger(TaskStatusManagerImpl.class);

  private boolean cancelTaskRequested;
  private boolean timerElapsed;
  private Thread timerThread;

  public boolean isCancelTaskRequested() {
    return cancelTaskRequested;
  }

  /**
   * Marks current service task as cancel required and sets timeout for cancellation
   *
   * @param timeout time for service to cancel current task. When timeout will elapsed {@link
   * TaskStatusManager} will throw an exception on next invocation of  {@link
   * TaskStatusManager#isHardCancelRequired }
   */
  public boolean requestTaskCancel(Integer timeout) {
    if (timeout != null && timeout > 0) {
      timerThread = new Thread(() -> {
        try {
          Thread.sleep(timeout * 1000);
          logger.info(
              "Cancel timer of " + timeout + " seconds elapsed - current task will be canceled throw Exception.");
          timerElapsed = true;
        } catch (InterruptedException e) {
          //stop timer, called cleanUp();
          logger.info("TaskStatusManager cleaned up before Cancel timer elapsed.");
        }
      });
      timerThread.start();
    }
    return cancelTaskRequested = true;
  }

  public void cleanUp() {
    cancelTaskRequested = false;
    timerElapsed = false;
    if (timerThread != null) {
      timerThread.interrupt();
    }
  }

  /**
   * Checks rather timeout for cancellation is elapsed and throws {@link RuntimeException} if it is.
   *
   * @return 'false' if timer is not elapsed, otherwise throws  {@link RuntimeException}
   */
  public boolean isHardCancelRequired() {
    if (timerElapsed) {
      logger.info("Cancellation timed out. Forcing hard cancel.");
      throw new RuntimeException("Service was force canceled due to timeout");
    }
    return false;
  }

  /**
   * Marks transaction for current service task as rollback
   */
  public void rollbackTask() {
    TransactionAspectSupport.currentTransactionStatus()
        .setRollbackOnly();
    cleanUp();
    logger.info("Task stopped on user CANCEL request. Transaction marked as RollbackOnly.");
  }
}
