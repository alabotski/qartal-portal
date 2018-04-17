package com.nomis.rabbit.status;

/**
 * TaskStatusManager.
 *
 * @author Alexander Sokolov.
 */
public interface TaskStatusManager {

  boolean requestTaskCancel(Integer timeout);

  boolean isCancelTaskRequested();

  boolean isHardCancelRequired();

  void rollbackTask();

  void cleanUp();
}
