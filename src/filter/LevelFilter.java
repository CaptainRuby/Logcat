package filter;

public class LevelFilter extends Filter {

    public static final int NONE = -1;
    public static final int Verbose = 0;
    public static final int Debug = 1;
    public static final int Info = 2;
    public static final int Warn = 3;
    public static final int Error = 4;
    public static final int Assert = 5;

    private int level;

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean filtrate(String line) {
        int level = getLevel(line.charAt(19));
        if (this.level <= level){
            if (nextFilter==null){
                return true;
            }
            return nextFilter.filtrate(line);
        }else {
            return false;
        }
    }

    private int getLevel(char c) {
        switch (c) {
            case 'V':
                return Verbose;
            case 'D':
                return Debug;
            case 'I':
                return Info;
            case 'W':
                return Warn;
            case 'E':
                return Error;
            case 'A':
                return Assert;
            default:
                return NONE;
        }
    }
}
