package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.data.user.CreditMapper;
import com.example.hotel.enums.HotelService;
import com.example.hotel.enums.VIPType;
import com.example.hotel.po.Credit;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String REGISTRATION_SUCCESS = "注册成功";
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    private final static String USER_NOTEXIST = "用户不存在";

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CreditMapper creditMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setPassword(PasswordEncryptHelper.getMD5(user.getPassword()));
        user.setAnnulTime(3);
        try {
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(REGISTRATION_SUCCESS);
    }

    @Override
    public UserVO login(UserForm userForm) {
        User user = accountMapper.getAccountByEmail(userForm.getEmail());
        if (!user.getPassword().equals(PasswordEncryptHelper.getMD5(userForm.getPassword()))) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public UserVO getUserInfo(int id) {
        User user = accountMapper.getAccountById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public UserVO getUserInfoByEmail(String email) {
        User user;
        try {
            user = accountMapper.getAccountByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (user != null) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        } else {
            return null;
        }
        //最好修改成ResponseVO，方便前端查看警告信息
    }

    @Override
    public ResponseVO updateUserInfo(int id, String password, String username, String phoneNumber) {
        try {
            accountMapper.updateAccount(id, PasswordEncryptHelper.getMD5(password), username, phoneNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }


    @Override
    public ResponseVO updateCredit(int id, double credit) {
        try {
            accountMapper.updateCredit(id, credit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO argueCredit(int creditId, String argue) {
        Credit credit = creditMapper.getCredit(creditId);
        if (credit.getStatus() != 0) {
            return ResponseVO.buildFailure("无法申诉");
        }
        creditMapper.argueCredit(creditId, argue);
        return ResponseVO.buildSuccess("申诉成功");
    }

    @Override
    public List<CreditVO> getArgueCredits() {
        List<Credit> credits = creditMapper.getArgueCredits();
        List<CreditVO> creditVOS = new ArrayList<>();
        for (Credit credit : credits) {
            CreditVO creditVO = new CreditVO();
            BeanUtils.copyProperties(credit, creditVO);
            creditVOS.add(creditVO);
        }
        return creditVOS;
    }

    @Override
    public ResponseVO handleArgue(Integer creditId, boolean accept) {
        int status = accept ? 2 : -1;
        return ResponseVO.buildSuccess(creditMapper.handleArgue(creditId, status));
    }

    //    @Override
//    public ResponseVO personalVIP(int id, String birthday) {
//        try {
//            accountMapper.updateBirthday(id, birthday);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseVO.buildFailure(UPDATE_ERROR);
//        }
//        return ResponseVO.buildSuccess(true);
//    }
//
    @Override
    public ResponseVO corporateVIP(int id, String corporate) {
        try {
            accountMapper.updateCorporate(id, corporate);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

//    @Override
//    public ResponseVO normalUser(String corporate) {
//        return null;
//    }

    @Override
    public void updateBirthday(int id, String birthday) {
        try {
            accountMapper.updateBirthday(id, birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerAsVIP(int id) {
        try {
            accountMapper.updateVIPType(id, VIPType.Client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void freezeVIP(int id) {
        try {
            accountMapper.updateVIPType(id, VIPType.Normal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Deprecated
    public ResponseVO updatePortrait(int userId, String url) {
        try {
            accountMapper.updatePortrait(userId, url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess();
    }


    @Override
    public ResponseVO chargeCredit(int userId, int change, String reason) {
        try {
            accountMapper.chargeCredit(userId, change);
            User user = accountMapper.getAccountById(userId);
            creditMapper.addCredit(new Credit(userId, change, user.getCredit(), reason));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getUserCreditChanges(int userId) {
        List<CreditVO> res = new ArrayList<>();
        try {
            List<Credit> src = creditMapper.getUserCredit(userId);
            for (Credit credit : src) {
                CreditVO creditVO = new CreditVO();
                BeanUtils.copyProperties(credit, creditVO);
                res.add(creditVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(USER_NOTEXIST);
        }

        return ResponseVO.buildSuccess(res);
    }

    @Override
    public List<UserVO> getAllUsers() {
        List<User> users = accountMapper.getAllUsers();
        List<UserVO> userVOs = new ArrayList<>();
        for (User user : users) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOs.add(userVO);
        }
        return userVOs;
    }
}
