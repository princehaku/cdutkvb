/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-20, 9:02:43
 */

package net._3haku.kvb.coursetable;

/**单元格
 * @author princehaku
 */
public abstract class TableColumn{

    /**单元格跨度
     */
    protected int crossSpan;
    /**设置单元格跨度
     *
     * @param crossSpan
     */
    public void setCrossSpan(int crossSpan) {
        this.crossSpan = crossSpan;
    }

    public int getCrossSpan() {
        return crossSpan;
    }
}
