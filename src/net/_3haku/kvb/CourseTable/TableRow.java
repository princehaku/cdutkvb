/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-20, 9:02:32
 */

package net._3haku.kvb.CourseTable;

import java.util.ArrayList;

/**
 *
 * @author princehaku
 */
public abstract class TableRow {
    /**单元格们
     *
     */
   protected ArrayList<TableColumn> colums;
     /**得到单元格们
      * @param colums
      * @return ArrayList<TableColumn>
      */
    public ArrayList<TableColumn> getColums(){
        return colums;
    }
     /**设置单元格
      * @param colums
      */
    public void setColums(ArrayList<TableColumn> colums){
       this.colums=colums;
    }

}
