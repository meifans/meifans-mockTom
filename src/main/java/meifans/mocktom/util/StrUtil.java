package meifans.mocktom.util;

/**
 * Created by Meifans on 2016/8/8.
 */
public final class StrUtil {

	public static int indexOf(String source, String target) {
		return isBlank(source, target) ? -1 : indexOf(source.toCharArray(), target.toCharArray());
	}

	public static int indexOf(char[] source, char[] target) {
		return indexOf(source, target, 0, target.length);
	}

	public static int indexOf(char[] source, char[] target, int start, int offset) {
		if (isBlank(source, target) || source.length < offset || start < 0
					|| offset > target.length || (offset - start > target.length)) {
			return -1;

		}
		int[] number = palindrome(target);
		int i = 0, j = start, count = 0;
		while (i < source.length && j < offset) {
			if (source[i] == target[j]) {
				i++;
				j++;
				count++;
			} else if (j != start && count != 0) {
				j = number[j - 1];
			} else {
				count = 0;
				i++;
				j++;
			}
		}
		if (i <= source.length && j == offset) {
			return i - j;
		}
		return -1;

	}

	private static int[] palindrome(char[] target) {
		return palindrome(target, 0, target.length);
	}

	private static int[] palindrome(char[] target, int start, int offset) {
		int[] number = new int[offset];
		number[0] = 0;
		int j;
		for (int i = start + 1; i < offset; i++) {
			j = i - 1;
			while (j != 0 && target[i] != target[start + number[j]]) {
				j = number[j - 1];
			}
			number[i] = number[j] + 1;
		}
		return number;
	}

	public static boolean isBlank(String s) {
		return s == null || s.equals("");
	}

	private static boolean isBlank(char[] source, char[] target) {
		return source == null || target == null || source.length == 0 || target.length == 0;
	}

	private static boolean isBlank(String s, String s2) {
		return isBlank(s) || isBlank(s2);
	}
}
