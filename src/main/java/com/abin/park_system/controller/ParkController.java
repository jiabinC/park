package com.abin.park_system.controller;

import com.abin.park_system.entity.Image;
import com.abin.park_system.entity.Order;
import com.abin.park_system.entity.Park;
import com.abin.park_system.entity.Users;
import com.abin.park_system.service.ImageService;
import com.abin.park_system.service.OrderService;
import com.abin.park_system.service.ParkService;
import com.abin.park_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/car")
public class ParkController {
	@Autowired
	private ParkService parkService;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

	List<String> imageName;

	@RequestMapping("/car-detail")
	public String cardetail() {
		return "car/car-detail";
	}
	@RequestMapping("/car-select")
	public String getCarByKey(Model model, HttpServletRequest httpServletRequest) {
		String user_id = httpServletRequest.getParameter("user_id");
		List<Park> parkList = parkService.findParkByUserId(user_id);
		model.addAttribute("parkList",parkList);
		model.addAttribute("parkNum",parkList.size());
		model.addAttribute("check_status","-1");
        model.addAttribute("publish_status","-1");
        model.addAttribute("show","0");

		return "list";
	}
	@RequestMapping("/carAdd")
	public String carAdd() {
		return "park/parkAdd";
	}
	@RequestMapping("/carUpload")
	public String carUpload(@RequestParam("park_id") String park_id,Model model) {
		model.addAttribute("park_id",park_id);
		return "park/parkUpload";
	}

	@RequestMapping("/image")
	public String image(@RequestParam("park_id") String park_id, Model model ){
		   List<Image> imageList =  imageService.selectById(park_id);
        List <String> imagePath = new ArrayList<String>();
		 System.out.println(imageList.size());

		 for(int i =0 ;i<imageList.size();i++)
         {
             imagePath.add(imageList.get(i).getImg_path());
         }


		 model.addAttribute("imagePath",imagePath);
		 return  "car/image";
	}


	@RequestMapping("/photoUpload")
	@ResponseBody
	public String  photoUpload(@RequestParam("park_id") String park_id, MultipartFile file,HttpSession httpSession) {
		String path = "G:\\毕业设计\\park_system\\src\\main\\resources\\static\\image-upload\\"+httpSession.getAttribute("user_id")+"\\";
		File file0 = new File(path);
		File file1 =new File(path+file.getOriginalFilename());
		if (!file0.isDirectory()) {
			file0.mkdir();
		}
		if (!file.isEmpty()) {

				if (file1.exists()) {
					return "{\"code\":1,\"msg\":\"图片已存在\"}";
				}
				else {
					try {
						Image image = new Image();
						image.setPark_id(park_id);
						image.setImg_path("/image-upload/"+httpSession.getAttribute("user_id")+"/" + file.getOriginalFilename());
						imageService.insert(image);
						Files.copy(file.getInputStream(), Paths.get(path, file.getOriginalFilename()));
						List<Image> imageList =imageService.selectById(park_id);

						return "{\"code\":0,\"msg\":\"上传成功\"}";


					}
					catch (Exception e) {
						System.out.println(e);
						return "{\"code\":1，\"msg\":\"上传失败\"}";
					}
				}
		}

		else  {
			return "{\"code\":1,\"msg\":\"文件为空\"}";
		}
//		String fileName = file.getOriginalFilename();
//		System.out.println(fileName);
//		System.out.println(park_id);

	}

	@RequestMapping(value = "/check")
	public String carCheck(@RequestParam("park_id") String id,Model model) {
		Park park = parkService.getById(id);
		model.addAttribute("parkList",park);
		return "car/check";
	}

	@ResponseBody
	@RequestMapping("/car-save")
	public Map<String,String> saveCar(@ModelAttribute("park") Park park,HttpSession httpSession) {
		park.setUser_id(httpSession.getAttribute("user_id").toString());
		Map<String,String> map = new HashMap<String,String>();
		map.put("res", "0");
		if (parkService.addCar(park))
		{
			map.put("park_id",park.getPark_id());
			map.put("res", "0");
		}
		return map;

	}
    @RequestMapping("/show_adminRemark")
    public String show_adminRemark(Model model,@RequestParam("park_id") String park_id) {
        Park park = parkService.getById(park_id);
        model.addAttribute("admin_remark",park.getAdmin_remark());
        return "admin/remark";

    }
	@ResponseBody
	@RequestMapping("/car-check")
	public Map<String,String> checkCar(@ModelAttribute("park") Park park, @RequestParam("check_status") String status) {
		Map<String,String> map = new HashMap<String,String>();
		if (status.contains("通过")) {
			park.setCheck_status("审核通过");
		}
		else {
			park.setCheck_status("审核拒绝");
		}
		System.out.println(park.getAdmin_remark());
		System.out.println(park.getPark_id());
		if (parkService.check(park))
		{
			map.put("res", "0");
		}
		else {
			map.put("res","1");
		}
		return map;

	}
	
	@RequestMapping("/findCar")
	public String findCar(@RequestParam("check_status") String check_status,@RequestParam("publish_status") String publish_status, Model model) {
		model.addAttribute("check_status",check_status);
        model.addAttribute("publish_status",publish_status);
        model.addAttribute("show","0");
		if(check_status.equals("-1")) {
			List<Park> parkLists = parkService.getAll();
			List<Park> parkList = new ArrayList<Park>();
			if (publish_status.equals("-1")) {
			    parkList = parkLists;
            }
            if (publish_status.equals("0")) {
                for (int i = 0; i < parkLists.size(); i++) {
                    if (parkLists.get(i).getPublish_status().equals("未发布")) {
                        parkList.add(parkLists.get(i));
                    }
                }
            }
            if (publish_status.equals("1")) {
                for (int i = 0; i < parkLists.size(); i++) {
                    if (parkLists.get(i).getPublish_status().equals("已发布")) {
                        parkList.add(parkLists.get(i));
                    }
                }
            }
			model.addAttribute("parkList",parkList);
		}
        if(check_status.equals("0")) {
            List<Park> parkLists = parkService.findParkByCheckStatus("审核中");
            List<Park> parkList = new ArrayList<Park>();
            if (publish_status.equals("-1")) {
                parkList = parkLists;
            }
            if (publish_status.equals("0")) {
                for (int i = 0; i < parkLists.size(); i++) {
                    if (parkLists.get(i).getPublish_status().equals("未发布")) {
                        parkList.add(parkLists.get(i));
                    }
                }
            }
            if (publish_status.equals("1")) {
                for (int i = 0; i < parkLists.size(); i++) {
                    if (parkLists.get(i).getPublish_status().equals("已发布")) {
                        parkList.add(parkLists.get(i));
                    }
                }
            }
            model.addAttribute("parkList",parkList);
        }
        if(check_status.equals("1")) {
            List<Park> parkLists = parkService.findParkByCheckStatus("审核通过");
            List<Park> parkList = new ArrayList<Park>();
            if (publish_status.equals("-1")) {
                parkList = parkLists;
            }
            if (publish_status.equals("0")) {
                for (int i = 0; i < parkLists.size(); i++) {
                    if (parkLists.get(i).getPublish_status().equals("未发布")) {
                        parkList.add(parkLists.get(i));
                    }
                }
            }
            if (publish_status.equals("1")) {
                for (int i = 0; i < parkLists.size(); i++) {
                    if (parkLists.get(i).getPublish_status().equals("已发布")) {
                        parkList.add(parkLists.get(i));
                    }
                }
            }
            model.addAttribute("parkList",parkList);
        }
        if(check_status.equals("2")) {
            List<Park> parkLists = parkService.findParkByCheckStatus("审核拒绝   ");
            List<Park> parkList = new ArrayList<Park>();
            if (publish_status.equals("-1")) {
                parkList = parkLists;
            }
            if (publish_status.equals("0")) {
                for (int i = 0; i < parkLists.size(); i++) {
                    if (parkLists.get(i).getPublish_status().equals("未发布")) {
                        parkList.add(parkLists.get(i));
                    }
                }
            }
            if (publish_status.equals("1")) {
                for (int i = 0; i < parkLists.size(); i++) {
                    if (parkLists.get(i).getPublish_status().equals("已发布")) {
                        parkList.add(parkLists.get(i));
                    }
                }
            }
            model.addAttribute("parkList",parkList);
        }
		return "list";
	}
	@RequestMapping("/detail")
	public String editCar(@RequestParam("id") String id, Model model){
		Park car = parkService.getById(id);
		model.addAttribute("car",car);
		return "detail";
	}
	
//	@RequestMapping("/buy")
//	public String buy(HttpSession session, @RequestParam("id") int id){
//		Park car = parkService.getById(id);
//		Users user = (Users)session.getAttribute("LogUser");
//		int p = user.getPoint();
//		car.setStatus(1);
//		if(parkService.updateCarStatus(car)) {
//			Order order =new Order();
//			order.setUser_id(user.getId());
//			order.setPark_id(id);
//			if(p>=100&&p<300) {
//				order.setTotal(car.getPrice()*0.9);
//			}else if(p>=300&&p<500) {
//				order.setTotal(car.getPrice()*0.8);
//			}else if(p>=500) {
//				order.setTotal(car.getPrice()*0.7);
//			}
//			if(orderService.addOrder(order)) {
//				user.setPoint(p+10);
//				userService.updatePoint(user);
//			}
//		}
//		return "redirect:/order/showOrder";
//	}
}
