package pre_process_rule;

public class GenID {
	private static int id = 11;
	public static String getId() {
		return "_0" + String.valueOf(id++);
	}
}
