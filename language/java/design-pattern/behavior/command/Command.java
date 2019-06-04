package gu.java.pattern.behavior.command;

/**
 * Interfaces for Commands
 * @author gudongxian
 * @create 2018-04-29 上午8:03
 **/
public abstract class Command {

    public abstract void execute(Target traget);

    public abstract void undo();

    public abstract void redo();

    @Override
    public abstract String toString();
}
