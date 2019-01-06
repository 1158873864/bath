package bath.blservice.groupon;

import bath.exception.NotExistException;
import bath.response.groupon.*;

import java.util.Date;

public interface GrouponBlService {
    /**
     * 管理员添加团购
     * @param name
     * @param originalPrice
     * @param price
     * @param description
     * @return 是否成功
     */
    GrouponAddResponse addGroupon(String name, double originalPrice, double price, Date takeEffectTime,Date loseEffectTime,Date putOnShelvesTime,Date pullOffShelvesTime, String description,int amount);

    /**
     * 管理员删除团购
     * @param id
     * @return
     */
    GrouponDeleteResponse deleteGroupon(String id)throws NotExistException;
   /**
    * 管理员修改团购
    * @param id
    * @param name
    * @param originalPrice
    * @param price
    * @param takeEffectTime
    * @param loseEffectTime
    * @param putOnShelvesTime
    * @param pullOffShelvesTime
    * @param description
    * @param amount
    * @return 是否成功
    */
    GrouponUpdateResponse updateGroupon(String id,String name, double originalPrice, double price, Date takeEffectTime,Date loseEffectTime, Date putOnShelvesTime,Date pullOffShelvesTime,String description,int amount)throws NotExistException;

    /**
     * 管理员上架团购
     * @param id
     * @return 是否成功
     */
    GrouponUpdateResponse putOnShelves(String id)throws NotExistException;

    /**
     * 管理员下架团购
     * @param id
     * @return 是否成功
     */
    GrouponUpdateResponse pullOffShelves(String id)throws NotExistException;

    /**
     * 通过id查找团购
     * @param id
     * @return 团购
     */
    GrouponResponse findById(String id)throws NotExistException;

    /**
     * 查找所有的团购
     * @param
     * @return 所有团购
     */
    GrouponListResponse getAll();

    /**
     * 查找已经上架的团购
     * @param
     * @return
     */
    GrouponListResponse getAvailableGroupon()throws NotExistException;


}
