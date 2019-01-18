package filter;

public class TagFilter extends Filter {

    private String tag;
    
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean filtrate(String line) {
        String tag = line.substring(21,line.indexOf('('));
        if (tag.contains(this.tag)){
            if (nextFilter==null){
                return true;
            }
            return nextFilter.filtrate(line);
        }else {
            return false;
        }
    }

}
