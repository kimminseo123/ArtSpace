package com.example.demo.mypage.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.file.dao.FileDAO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.hall.dto.HallDTO;
import com.example.demo.hall.dto.ReviewDTO;
import com.example.demo.mypage.dao.MypageDAO;
import com.example.demo.mypage.dto.LikeDTO;
import com.example.demo.mypage.dto.PerformerDTO;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.dto.ReservationEquipmentDTO;
import com.example.demo.reservation.dto.ReserveDateDTO;
import com.example.demo.user.dto.UserDTO;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageDAO mypageDAO;
	
	@Autowired
	FileDAO fileDAO;

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
		List<HallDTO> likeList = mypageDAO.getAllLike(id);
		
		for (HallDTO hallDTO : likeList) {
			FileDTO mainImage = fileDAO.getHallMainFile(hallDTO.getHall_id());
			
			hallDTO.setMainImage(mainImage);
		}
		
		return likeList;
	}

	@Override
	public void likeDelete(Integer user_id, Integer hall_id) {
		mypageDAO.likeDelete(user_id, hall_id);
		
	}

	@Override
	public List<ReservationDTO> getAllReserve(Integer user_id) {
		List<ReservationDTO> reserveList = mypageDAO.getAllReserve(user_id);
		
		for (ReservationDTO reservationDTO : reserveList) {
			FileDTO mainImage = fileDAO.getHallMainFile(reservationDTO.getHall_id());
			reservationDTO.setMainImage(mainImage);
			
			int reserve_id = reservationDTO.getReserve_id();
			reservationDTO.setReservationEquipmentList(mypageDAO.getAllReservationEquip(reserve_id));
			reservationDTO.setReserveDateList(mypageDAO.getAllReserveDate(reserve_id));
		} 
		return reserveList;
	}
	
	@Override
	public Map<Integer, LocalDateTime> getEarliestReserveDates(List<ReservationDTO> reservationList) {
	    Map<Integer, LocalDateTime> earliestDates = new HashMap<>();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    for (ReservationDTO reservation : reservationList) {
	        List<ReserveDateDTO> reserveDateList = reservation.getReserveDateList();
	        if (reserveDateList != null && !reserveDateList.isEmpty()) {
	            LocalDateTime earliestDate = null;
	            for (ReserveDateDTO reserveDate : reserveDateList) {
	                String dateString = reserveDate.getReserve_date().trim();
	                LocalDateTime date = LocalDateTime.parse(dateString, formatter);
	                if (earliestDate == null || date.isBefore(earliestDate)) {
	                    earliestDate = date;
	                }
	            }
	            earliestDates.put(reservation.getReserve_id(), earliestDate);
	        }
	    }
	    return earliestDates;
	}
	
	@Override
	public void reserveDelete(Integer reserve_id) {
		
		mypageDAO.reserveDelete(reserve_id);
	}

	@Override
	public ReservationDTO reserveDetail(Integer reserve_id) {
		
		return mypageDAO.reserveDetail(reserve_id);
	}

	@Override
	public void updatePhone(UserDTO dto) {
		mypageDAO.updatePhone(dto);
	}

	@Override
	public List<ReservationDTO> getNotReview(Integer user_id) {
		List<ReservationDTO> notReviewList = mypageDAO.getNotReview(user_id);
		
		for (ReservationDTO reservationDTO : notReviewList) {
			FileDTO mainImage = fileDAO.getHallMainFile(reservationDTO.getHall_id());
			reservationDTO.setMainImage(mainImage);

			int reserve_id = reservationDTO.getReserve_id();
			reservationDTO.setReservationEquipmentList(mypageDAO.getAllReservationEquip(reserve_id));
			reservationDTO.setReserveDateList(mypageDAO.getAllReserveDate(reserve_id));
		}
		
		return notReviewList;
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

	@Override
	public List<ReservationEquipmentDTO> getAllReservationEquip(Integer reserve_id) {
		
		return mypageDAO.getAllReservationEquip(reserve_id);
	}

	@Override
	public List<ReserveDateDTO> reserveDate(Integer reserve_id) {
		
		return mypageDAO.getAllReserveDate(reserve_id);
	}




	
}
