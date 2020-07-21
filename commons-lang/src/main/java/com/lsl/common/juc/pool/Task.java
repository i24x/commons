package com.lsl.common.juc.pool;

public class Task implements Runnable {

    private int taskId;
    private String taskName;

    public Task(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("run taskId =" + this.taskId);
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", taskName=" + taskName + "]";
    }

    public static void main(String[] args) {
        int m = -536870910;
        System.out.println(Integer.toBinaryString(m));
        System.out.println(Integer.toBinaryString((1 << 29) - 1));
        System.out.println(m & ((1 << 29) - 1));
    }
}
