package filter;

public class FilterChain extends Filter{

    Filter filter;

    public void addFilter(Filter filter){
        if (this.filter==null){
           this.filter = filter;
           return;
        }
        Filter p = this.filter;
        while (p.nextFilter!=null){
            p = p.nextFilter;
        }
        p.nextFilter = filter;
    }

    @Override
    public boolean filtrate(String line) {
        return filter.filtrate(line);
    }
}
