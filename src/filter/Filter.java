package filter;

public abstract class Filter {

    public Filter nextFilter;

    abstract boolean filtrate(String line);
}
