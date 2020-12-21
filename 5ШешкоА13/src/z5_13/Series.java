package z5_13;

public abstract class Series  {

	public abstract float getBj(int j, float q, float b1);
	public abstract float getBj(int j);
	public abstract float sum(int n, float q, float b1);
	public abstract float sum(int n);
	
	public static class ArgException extends Exception {
		private static final long serialVersionUID = 1L;

		ArgException(String arg) {
			super("Invalid argument: " + arg);
		}
	}
}
