package cn.iocoder.yudao.adminserver.modules.system.service.user;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.adminserver.modules.system.controller.user.vo.profile.SysUserProfileUpdatePasswordReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.user.vo.profile.SysUserProfileUpdateReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.user.vo.user.*;
import cn.iocoder.yudao.coreservice.modules.system.dal.dataobject.user.SysUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;

import java.io.InputStream;
import java.util.*;

/**
 * 后台用户 Service 接口
 *
 * @author 芋道源码
 */
public interface SysUserService {

    /**
     * 创建用户
     *
     * @param reqVO 用户信息
     * @return 用户编号
     */
    Long createUser(SysUserCreateReqVO reqVO);

    /**
     * 修改用户
     *
     * @param reqVO 用户信息
     */
    void updateUser(SysUserUpdateReqVO reqVO);

    /**
     * 更新用户的最后登陆信息
     *
     * @param id 用户编号
     * @param loginIp 登陆 IP
     */
    void updateUserLogin(Long id, String loginIp);

    /**
     * 修改用户个人信息
     *
     * @param id 用户编号
     * @param reqVO 用户个人信息
     */
    void updateUserProfile(Long id, SysUserProfileUpdateReqVO reqVO);

    /**
     * 修改用户个人密码
     *
     * @param id 用户编号
     * @param reqVO 更新用户个人密码
     */
    void updateUserPassword(Long id, SysUserProfileUpdatePasswordReqVO reqVO);

    /**
     * 更新用户头像
     *
     * @param id         用户 id
     * @param avatarFile 头像文件
     */
    String updateUserAvatar(Long id, InputStream avatarFile);

    /**
     * 修改密码
     *
     * @param id       用户编号
     * @param password 密码
     */
    void updateUserPassword(Long id, String password);

    /**
     * 修改状态
     *
     * @param id     用户编号
     * @param status 状态
     */
    void updateUserStatus(Long id, Integer status);

    /**
     * 删除用户
     *
     * @param id 用户编号
     */
    void deleteUser(Long id);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    SysUserDO getUserByUsername(String username);

    /**
     * 获得用户分页列表
     *
     * @param reqVO 分页条件
     * @return 分页列表
     */
    PageResult<SysUserDO> getUserPage(SysUserPageReqVO reqVO);

    /**
     * 获得用户
     *
     * @param id 用户编号
     * @return 用户
     */
    SysUserDO getUser(Long id);

    /**
     * 获得用户列表
     *
     * @param reqVO 列表请求
     * @return 用户列表
     */
    List<SysUserDO> getUsers(SysUserExportReqVO reqVO);

    /**
     * 获得用户列表
     *
     * @param ids 用户编号数组
     * @return 用户列表
     */
    List<SysUserDO> getUsers(Collection<Long> ids);

    /**
     * 获得用户 Map
     *
     * @param ids 用户编号数组
     * @return 用户 Map
     */
    default Map<Long, SysUserDO> getUserMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return new HashMap<>();
        }
        return CollectionUtils.convertMap(getUsers(ids), SysUserDO::getId);
    }

    /**
     * 获得用户列表，基于昵称模糊匹配
     *
     * @param nickname 昵称
     * @return 用户列表
     */
    List<SysUserDO> getUsersByNickname(String nickname);

    /**
     * 获得用户列表，基于用户账号模糊匹配
     *
     * @param username 用户账号
     * @return 用户列表
     */
    List<SysUserDO> getUsersByUsername(String username);

    /**
     * 批量导入用户
     *
     * @param importUsers     导入用户列表
     * @param isUpdateSupport 是否支持更新
     * @return 导入结果
     */
    SysUserImportRespVO importUsers(List<SysUserImportExcelVO> importUsers, boolean isUpdateSupport);

    /**
     * 获得指定状态的用户们
     *
     * @param status 状态
     * @return 用户们
     */
    List<SysUserDO> getUsersByStatus(Integer status);

    /**
     * 获得指定岗位的用户数组
     *
     * @param postIds 岗位数组
     * @return 用户数组
     */
    List<SysUserDO> getUsersByPostIds(Collection<Long> postIds);

    /**
     * 获得指定部门的用户数组
     *
     * @param deptIds 部门数组
     * @return 用户数组
     */
    List<SysUserDO> getUsersByDeptIds(Collection<Long> deptIds);

    /**
     * 校验用户们是否有效。如下情况，视为无效：
     * 1. 用户编号不存在
     * 2. 用户被禁用
     *
     * @param ids 用户编号数组
     */
    void validUsers(Set<Long> ids);

}
