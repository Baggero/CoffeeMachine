package se.coffeemachine.fragments;

public interface SwipeContext {

	public boolean handleMessage(int what);

	public boolean handleMessage(int what, Object data);

}
