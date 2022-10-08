 class MyString {
    private String str = " ";

    public MyString(String str) {
        this.str = str; // save string
    }
    public String[] separateStringConsole() {
        return str.split(" "); // split string by spaces
    }
}
