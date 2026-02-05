public class Marker {

    public static void mark(String[] catalogue, int fill, int position, String line) {
        int idx = position - 1;

        if (!isValid(idx, fill, catalogue)) {
            System.out.println(line + "Invalid task number.\n" + line);
            return;
        }

        catalogue[idx] = catalogue[idx].replaceFirst("\\[ \\]", "[x]");
        System.out.println(line
                + "Nice! I've marked this task as done:\n"
                + catalogue[idx] + "\n" + line);
    }

    public static void unmark(String[] catalogue, int fill, int position, String line) {
        int idx = position - 1;

        if (!isValid(idx, fill, catalogue)) {
            System.out.println(line + "Invalid task number.\n" + line);
            return;
        }

        catalogue[idx] = catalogue[idx].replaceFirst("\\[x\\]", "[ ]");
        System.out.println(line
                + "Nice! I've marked this task as not done yet:\n"
                + catalogue[idx] + "\n" + line);
    }

    private static boolean isValid(int idx, int fill, String[] catalogue) {
        return idx >= 0 && idx < fill && catalogue[idx] != null;
    }
}
