/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-31, 14:22:59
 */

package net._3haku.kvb.TimeFactory;

import java.util.Date;

/**学校时间
 *
 * @author princehaku
 */
public interface SchoolTime {
    /**设置时间
     *
     */
    void setTime();
    /**得到第X节课的开始上课时间 XX:XX 上课时间
     *@param int courseSt
     */
    Date getStartTimeAt(int courseSt);
    /**得到第X节课的下课时间 XX:XX 上课时间
     *@param int courseSt
     */
    Date getEndTimeAt(int courseSt);

    
}
