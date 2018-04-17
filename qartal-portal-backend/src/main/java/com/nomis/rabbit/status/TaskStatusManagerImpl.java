package com.nomis.rabbit.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * TaskStatusManagerImpl.
 *
 * @author Alexander Sokolov.
 */
@Slf4j
public class TaskStatusManagerImpl implements TaskStatusManager {

  private boolean cancelTaskRequested;
  private boolean timerElapsed;
  private Thread timerThread;

  public boolean isCancelTaskRequested() {
    return cancelTaskRequested;
  }

  /**
   * Marks current service task as cancel required and sets timeout for cancellation.
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
          log.info("Cancel timer of " + timeout + " seconds elapsed - current task will be canceled throw Exception.");
          timerElapsed = true;
        } catch (InterruptedException e) {
          //stop timer, called cleanUp();
          log.info("TaskStatusManager cleaned up before Cancel timer elapsed.");
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
      log.info("Cancellation timed out. Forcing hard cancel.");
      //      throw new RuntimeException("Service was force canceled due to timeout");
    }
    return false;
  }

  /**
   * Marks transaction for current service task as rollback.
   */
  public void rollbackTask() {
    TransactionAspectSupport.currentTransactionStatus()
        .setRollbackOnly();
    cleanUp();
    log.info("Task stopped on user CANCEL request. Transaction marked as RollbackOnly.");
  }
}
