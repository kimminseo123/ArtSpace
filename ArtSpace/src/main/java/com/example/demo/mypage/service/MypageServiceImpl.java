package com.example.demo.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReservationDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.mypage.dao.MypageDAO;
import com.example.demo.mypage.dto.LikeDTO;
import com.example.demo.mypage.dto.PerformerDTO;
import com.example.demo.user.dto.UserDTO;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageDAO mypageDAO;
	

	@Override
	public UserDTO findByID(Integer id) {
		// TODO Auto-generated method stub
			return mypageDAO.findById(id);
	}

	@Override
	public void updateNickname(UserDTO dto) {
		mypageDAO.updateNickname(dto);
		
	}

	@Override
	public void updatePw(UserDTO dto) {
		mypageDAO.updatePw(dto);
	}

	@Override
	public PerformerDTO findByPID(Integer id) {
		
		return mypageDAO.findByPID(id);
	}

	@Override
	public void insert(PerformerDTO dto) {
		
		PerformerDTO existPerformer = mypageDAO.findByPID(dto.getUser_id());
		if (existPerformer == null) {
			mypageDAO.insertPerformer(dto);
		} else {
			mypageDAO.updatePerformer(dto);
		}
	}

	@Override
	public void leave(UserDTO dto) {
		mypageDAO.leave(dto);
	}

	@Override
	public List<HallDTO> getAllLike(Integer id) {
		return mypageDAO.getAllLike(id);
	}

	@Override
	public void likeDelete(Integer user_id, Integer hall_id) {
		mypageDAO.likeDelete(user_id, hall_id);
		
	}

	@Override
	public List<ReservationDTO> getAllReserve(Integer user_id) {
		
		return mypageDAO.getAllReserve(user_id);
	}

//	@Override
//	public void reserveDelete(Integer user_id, Integer hall_id) {
//		
//		mypageDAO.reserveDelete(user_id, hall_id);
//	}
	
	@Override
	public void reserveDelete(Integer reserve_id) {
		
		mypageDAO.reserveDelete(reserve_id);
	}

	@Override
	public ReservationDTO reserveDetail(Integer reserve_id) {
		
		return mypageDAO.reserveDetail(reserve_id);
	}

	@Override
	public List<ReservationDTO> reserveEquip(Integer reserve_id) {
		// TODO Auto-generated method stub
		return mypageDAO.reserveEquip(reserve_id);
	}

	@Override
	public void updatePhone(UserDTO dto) {
		mypageDAO.updatePhone(dto);
	}

	@Override
	public List<HallDTO> getNotReview(Integer user_id) {
		
		return mypageDAO.getNotReview(user_id);
	}

	@Override
	public List<ReviewDTO> getReview(Integer user_id) {
		
		return mypageDAO.getReview(user_id);
	}

	@Override
	public void saveReview(ReviewDTO review) {
		
		mypageDAO.saveReview(review);
	}

	@Override
	public void updateReviewStatus(Integer reserve_id) {
		
		mypageDAO.updateReviewStatus(reserve_id);
	}




	
}
