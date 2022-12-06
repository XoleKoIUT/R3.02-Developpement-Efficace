package partie1;

public class RegionComparator implements java.util.Comparator<Dept> {
    @Override
    public int compare(Dept dept1, Dept dept2) {
        return dept1.getNomRegion().compareTo(dept2.getNomRegion());
    }
}