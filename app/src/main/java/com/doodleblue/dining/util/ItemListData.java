package com.doodleblue.dining.util;

import java.io.Serializable;

/**
 * Created by Boopathy Jagadeesan on 9/3/2020.
 */
public class ItemListData implements Serializable, Comparable {
    private String itemName;
    private String itemDesc;
    private int qtyAdded;
    private boolean isN;
    private boolean isD;
    private String itemRate;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getQtyAdded() {
        return qtyAdded;
    }

    public void setQtyAdded(int qtyAdded) {
        this.qtyAdded = qtyAdded;
    }

    public boolean isN() {
        return isN;
    }

    public void setN(boolean n) {
        isN = n;
    }

    public boolean isD() {
        return isD;
    }

    public void setD(boolean d) {
        isD = d;
    }

    public String getItemRate() {
        return itemRate;
    }

    public void setItemRate(String itemRate) {
        this.itemRate = itemRate;
    }

    public ItemListData(String itemName, String itemDesc, int qtyAdded, boolean isN, boolean isD, String itemRate) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.qtyAdded = qtyAdded;
        this.isD = isD;
        this.isN = isN;
        this.itemRate = itemRate;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        ItemListData compare = (ItemListData) o;
        if (compare.getItemName().equals(this.itemName) && compare.getQtyAdded()==(this.qtyAdded)){
            return 0;
        }
        return 1;
    }


}
