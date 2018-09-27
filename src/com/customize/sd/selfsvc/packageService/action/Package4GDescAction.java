package com.customize.sd.selfsvc.packageService.action;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;


/**
 * 4G套餐介绍
 * @author hWX5316476
 * @version  [版本号, 2014-12-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark OR_SD_201410_482_SD_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
 */
public class Package4GDescAction extends BaseAction
{
    /**
     * 序列化
     */ 
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志
     */ 
    public static final Log logger = LogFactory.getLog(Package4GDescAction.class);
    
    /**
     * 4G套餐描述列表
     */
    public List<DictItemPO> package4GDescList;
    
    /**
     * 查询4G套餐描述
     * @return
     */
    public String qryPackage4GDesc()
    {
        // 4G套餐描述列表
        package4GDescList = (List<DictItemPO>)this.getDictItemByGrp("pack4GDesc");
        
        // 记录错误日志信息
        this.createRecLog(Constants.SH_PACKAGE4G_DESC, "0", "0", "0", "4G套餐介绍查询成功！");
        
        // 没有合适的4G套餐介绍则跳转错误页面
        if(null == package4GDescList || package4GDescList.isEmpty())
        {
            // 设置错误信息
            getRequest().setAttribute("errormessage", "对不起，没有合适的4G套餐介绍！"); 
            
            return ERROR;
        }
        
        return "qryPackage4GDesc";
    }

    public List<DictItemPO> getPackage4GDescList()
    {
        return package4GDescList;
    }

    public void setPackage4GDescList(List<DictItemPO> package4GDescList)
    {
        this.package4GDescList = package4GDescList;
    }
}