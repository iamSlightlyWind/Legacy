public class openUrl {
    public static void main(String[] args) {
        String[] ID = { "9982", "9569", "10326", "10014", "10085", "9241", "10114", "10173", "10084", "10296", "9226",
                "10015", "10010", "10021", "9966", "9243", "9228", "10031", "10032", "10033" };
        // url example:
        // https://flm.fpt.edu.vn/gui/role/student/SyllabusDetails?sylID=9982
        for (int i = 0; i < ID.length; i++) {
            String url = "https://flm.fpt.edu.vn/gui/role/student/SyllabusDetails?sylID=" + ID[i];
            openWebpage(url);
        }
    }

    public static void openWebpage(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}