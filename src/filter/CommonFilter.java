package filter;

public class CommonFilter extends Filter {

    @Override
    public boolean filtrate(String line) {
        if (line.length()>18 && line.contains("(")){
            if (nextFilter==null){
                return true;
            }
            return nextFilter.filtrate(line);
        }else {
            return false;
        }
    }

}
