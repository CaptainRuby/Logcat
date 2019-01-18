package filter;

public class KeywordFilter extends Filter {

    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean filtrate(String line) {
        if (line.contains(keyword)){
            if (nextFilter==null){
                return true;
            }
            return nextFilter.filtrate(line);
        }else {
            return false;
        }
    }

}
