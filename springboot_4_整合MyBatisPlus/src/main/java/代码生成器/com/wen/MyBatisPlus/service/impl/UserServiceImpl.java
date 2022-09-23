package 代码生成器.com.wen.MyBatisPlus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import 代码生成器.com.wen.MyBatisPlus.entity.User;
import 代码生成器.com.wen.MyBatisPlus.mapper.UserMapper;
import 代码生成器.com.wen.MyBatisPlus.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wen
 * @since 2022-08-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
