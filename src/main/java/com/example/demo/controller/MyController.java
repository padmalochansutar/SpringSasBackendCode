package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import com.example.demo.bean.CollegeRegisterBean;
import com.example.demo.bean.EducationBean;

import com.example.demo.bean.PaymentDepositBean;
import com.example.demo.bean.RegisterBean;
import com.example.demo.entity.AccountHolder;
import com.example.demo.entity.Block;
import com.example.demo.entity.Cast;
import com.example.demo.entity.College;
import com.example.demo.entity.CollegeRegister;
import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.District;
import com.example.demo.entity.Education;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PaymentDeposit;
import com.example.demo.entity.Register;
import com.example.demo.entity.StudentSignin;
import com.example.demo.entity.Village;
import com.example.demo.entity.my_edu_my_reg;
import com.example.demo.repository.AccountHolderRepository;
import com.example.demo.repository.BlockRepository;
import com.example.demo.repository.CastRepository;
import com.example.demo.repository.CollegeRegisterRepository;
import com.example.demo.repository.CollegeRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.PaymentDepositRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.repository.StudentSigninRepository;
import com.example.demo.repository.my_edu_my_regRepository;
import com.example.demo.repository.villageRepository;
import com.example.demo.utils.CommonFileUpload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class MyController {
	@Autowired
	private StudentSigninRepository studentSigninRepository;
	@Autowired
	private RegisterRepository registerRepo;

	@Autowired
	private DistrictRepository districtRepo;
	@Autowired
	private BlockRepository blockRepo;
	@Autowired
	private villageRepository villageRepo;
	
	@Autowired
	private EducationRepository eduR;
	
	@Autowired
	private CourseRepository courRe;
	
	@Autowired
	private CollegeRepository collegeRepo;
	
	@Autowired
	private CollegeRegisterRepository collegeRegisterRepo;
	@Autowired
	private CastRepository castRepo;
	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
    private AccountHolderRepository accRepo;
	
	@Autowired
	private PaymentDepositRepository PaymentDepositrepo;
	
	@Autowired
	private my_edu_my_regRepository myRepo;
	Integer sId;
	@PostMapping("/signIn")
	public Map<String, Object> test(@RequestBody StudentSignin signin) {
		System.out.println("the name is;" + signin.getUserName() + "the password is:" + signin.getPassword());

		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> response1 = new HashMap<String, Object>();
		try {
			if (signin.getUserName().isEmpty() || signin.getPassword().isEmpty()) {
				response.put("failed", "please provide username and password");
				return response;
			}

		} catch (Exception e) {
			// return response;
		}

		StudentSignin searchByuserNameAndPassword = studentSigninRepository
				.searchByuserNameAndPassword(signin.getUserName(), signin.getPassword());
		System.out.println("the searchByuserNameAndPassword "+searchByuserNameAndPassword );
		try {
			sId=searchByuserNameAndPassword.getId();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		Register byName = registerRepo.finddataById(sId);
		System.out.println("the id is:"+searchByuserNameAndPassword.getId());
		if (searchByuserNameAndPassword == null) {
			response.put("failed", "No account!!please SignUp your Account");
			return response;
		} else if (signin.getUserName() != null || signin.getPassword() != null) {
			StudentSignin findByUserNameAndPassword = studentSigninRepository
					.findByUserNameAndPassword(signin.getUserName(), signin.getPassword());
			
		
			if (findByUserNameAndPassword != null) {
				System.out.println("the data is:" + findByUserNameAndPassword);
				response.put("success", "SuccessFully signin");
				response1.put("viewData", "Same Id");
				try {
				if(sId==byName.getId()) {
					return response1;
				}
				}catch (Exception e) {
					System.out.println("This exception handle null pointer exception");
				}
				return response;
			}
		}

		return null;
	}

	@PostMapping("/signUp")
	public Integer signUpData(@RequestBody StudentSignin signUp) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (signUp.getUserName().isEmpty() || signUp.getEmailId().isEmpty() || signUp.getMobileNo() == null
				|| signUp.getPassword().isEmpty()) {
			System.out.println("Failed reponse..........");
			return 1;
		}
		try {
			StudentSignin byEmailId = studentSigninRepository.getByEmailId(signUp.getEmailId());
			StudentSignin byMobileNo = studentSigninRepository.getByMobileNo(signUp.getMobileNo());
			if (byEmailId == null && byMobileNo == null) {
				studentSigninRepository.save(signUp);
				
				response.put("sucess", "sucess registered");
				return 2;
			} else if (byEmailId != null) {
				response.put("failed", "Already emailId Registered");
				return 3;
			} else if (byMobileNo != null) {
				response.put("failed", "Already mobileNo Registered");
				return 4;
			}
		} catch (Exception e) {
			System.err.println("some error occcured");
		}
		return null;

	}

	@PostMapping("/forgot")
	public Map<String, Object> forgotPassword(@RequestBody StudentSignin forgot) {
		// System.out.println("the data is:"+forgot);
		StudentSignin findByEmailId = studentSigninRepository.findByEmailId(forgot.getEmailId());
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			if (findByEmailId == null) {
				response.put("failed", "wrong mailId");
				return response;
			} else if (findByEmailId != null) {
				findByEmailId.setPassword(forgot.getPassword());
				StudentSignin save = studentSigninRepository.save(findByEmailId);
				response.put("success", "reset password");

				return response;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@GetMapping("/getdistrictData")
	public List<District> getDistrictData() {

		List<District> findAll = districtRepo.findAll();

//     Map<String, Object> response=new HashMap<String,Object>();
//     findAll.forEach(dist -> {
//    	 System.out.println(dist);
//     });
//     for (District district : findAll) {
//		System.out.println(district);
//	}
//     response.put("distList",findAll);
		return findAll;

	}

	@GetMapping("/getBlockDataByDistId/{id}")
	public List<Block> getBlockDataByDistId(@PathVariable Integer id) {
		List<Block> findByDistId = blockRepo.findBlockByDistrictId(id);
		// findByDistId.forEach(blockRepo ->{System.out.println(blockRepo);});
		return findByDistId;

	}

	@GetMapping("/getVillageDataByBlockId/{id}")
	public List<Village> getVillageDataByBlockId(@PathVariable Integer id) {
		List<Village> findByBlockId = villageRepo.findByBlockId(id);
		// findByDistId.forEach(blockRepo ->{System.out.println(blockRepo);});
		return findByBlockId;

	}
	
	String name="";
	String faName="";
	String moName="";
    Integer id;
	@PostMapping("/saveData")
	public Integer savedata(@RequestParam("data") String data, @RequestParam("file")MultipartFile file,@RequestParam("file1")MultipartFile file1) {
              
		System.out.println("the register data is:"+data);
		System.out.println(file.getOriginalFilename());
		System.out.println(file1.getOriginalFilename());
		//converet to json string data to registerBean
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		RegisterBean bean = null;
		try {
			bean = mapper.readValue(data, RegisterBean.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(bean);
//	List<EducationBean> educationBean = bean.getEduObj();
//	List<String> dataList=new ArrayList<>();
//	for(EducationBean bean:educationBean) {
//		
//		dataList.add(bean.getCourse());
//	}
//	String string = dataList.toString();
//	System.out.println("the course is:"+dataList);
		name=bean.getUserName();
		faName=bean.getFatherName();
		moName=bean.getMotherName();
		
		//Integer byName = registerRepo.getByName(name,faName,moName);used for counter
		Register byName = registerRepo.finddataById(sId);
		try {
		id=byName.getRegisterId();
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("...the name is"+byName);
		if(byName!=null) {
			//System.out.println("hello");
			return 0;
		}
		else  {
		Register register = new Register();
		register.setUserName(bean.getUserName());
		register.setFName(bean.getFatherName());
		register.setMName(bean.getMotherName());
		register.setDob(bean.getDob().substring(0, 10));
		register.setGender(bean.getGender());
		register.setCourse(bean.getCourse());
		register.setEdu(bean.getEduObj());// cut [] bracket...
		register.setState(bean.getState());
		//register.setFile1(bean.getFile1());
		District district = districtRepo.findById(bean.getDistrictId()).orElseThrow();
		register.setDistrict(district);
		Block block = blockRepo.findById(bean.getBlockId()).get();
		register.setBlock(block);
		Village village = villageRepo.findById(bean.getVillageObj()).get();
		register.setVillage(village);
		
		File f = new File("E:/FileUpload/" + file.getOriginalFilename());
		BufferedOutputStream bf = null;
		try {
			byte[] bytes = bean.getFile().getBytes();
			bf = new BufferedOutputStream(new FileOutputStream(f));
			bf.write(bytes);
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		register.setFile( file.getOriginalFilename());
		
		File f1 = new File("E:/FileUpload/" + file1.getOriginalFilename());
		BufferedOutputStream bf1 = null;
		try {
			byte[] bytes = bean.getFile1().getBytes();
			bf1 = new BufferedOutputStream(new FileOutputStream(f1));
			bf1.write(bytes);
			bf1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		register.setFile1("E:/FileUpload/" + file1.getOriginalFilename());
		register.setId(sId);
		System.out.println("............>.......>"+register);
	  Register save= registerRepo.save(register);
  System.out.println("the save data is:"+save);
	  if(save.getRegisterId()!=null) {
		  id=save.getRegisterId();
		  System.out.println("the save register id is:"+id);
		  return id;
  }
		  return -1;
		}
		
	}

	@GetMapping("/getAllEdu")
	public List<Education> getAllEducations() {
		return eduR.findAll();
	}
	
	@GetMapping("/getSelectedCourses/{id}")
	public List<CourseEntity> getSelectedCourses(@PathVariable(name = "id") Integer id) {
		return courRe.getAllList(id);
	}
	@GetMapping("/getAllCollgeData")
	public List<College> getAllCollgeData(){
		return collegeRepo.findAll();
	}
	@PostMapping("/getcollegeIdSave")
	public Integer collegeIdSave(@RequestBody CollegeRegisterBean data){
//		List<CollegeRegister> registerList=new ArrayList<>(); 
		System.out.println(data);
//		System.out.println(data.getCollegeId());
//		List<College> list=data.getCollegeId();
//		
		
		//Register regd = registerRepo.findById(data.getRegisterId()).get();
//        for(College x:list) {
//        	collegeRegisterRepo.saveAll()
//			
//		}
      // System.out.println("the  registerList"+registerList);
		Integer collegeRegisterId=0;
       // collegeRegisterRepo.saveAll(registerList);
		 for(College x:data.getCollegeId()) {
			 CollegeRegister register=new CollegeRegister();
			 Register regd = registerRepo.findById(data.getRegisterId()).get();
		    register.setCollege(x);
		 
		 register.setRegister(regd);
		 CollegeRegister save = collegeRegisterRepo.save(register);
		  collegeRegisterId = save.getCollegeRegisterId();
		 }
		
		return collegeRegisterId ;
		
		
	}
	@GetMapping("/getAllcast")
	public List<Cast> getAllCast() {
		System.out.println( castRepo.findAll());
		return castRepo.findAll();
	}
	@GetMapping("/getSelectedCast/{id}")
	public Double getSelectedCast(@PathVariable Integer id) {
		System.out.println(id);
		 Payment findById = paymentRepo.findById(id).get();
		 System.out.println( paymentRepo.findById(id).get());
		 return findById.getFee();
	}
	@GetMapping("/getAcno")
	public List<AccountHolder> getAcno() {
		System.out.println( castRepo.findAll());
		return accRepo.findAll();
	}
	@PostMapping("/getPayment")
	public PaymentDeposit paymentSave(@RequestBody PaymentDepositBean data) {
		System.out.println("the data payment is"+data);
		PaymentDeposit payment=new PaymentDeposit();
		payment.setAccNo(data.getAccountNumber());
		Cast castId = castRepo.findById(data.getCastId()).get();
		payment.setCast(castId);
		
		payment.setPayment(data.getPaymentFee());
		Register register = registerRepo.findById(data.getRegisterId()).get();
		payment.setRegister(register);
		payment.setId(sId);
		
		PaymentDeposit save = PaymentDepositrepo.save(payment);
		System.out.println("the save  data is:"+save);
		return save;
		
	}
	@GetMapping("/viewData")
	public PaymentDeposit getData(){
		System.out.println("pl");
		PaymentDeposit findDataId = PaymentDepositrepo.findDataId(sId);
		System.out.println("the findData Id is:"+findDataId);
		PaymentDeposit findAll = PaymentDepositrepo.findById(findDataId.getPaymentId()).get();
		System.out.println("the view data is:"+findAll);
	
		return findAll;
	}
	@DeleteMapping("/deleteData/{sId}")
	public Integer deleteData(@PathVariable Integer sId) {
		System.out.println("........>"+sId);
		if(sId!=null) {
			PaymentDeposit findId = PaymentDepositrepo.findByRegister(sId).get();
			System.out.println("...........>"+findId);
			
			Integer deletedataById = PaymentDepositrepo.deletedataById(findId.getPaymentId());
			System.out.println("...>"+deletedataById);
            List<my_edu_my_reg> findById = myRepo.findAll(sId);
			myRepo.deleteAll(findById);
			List<CollegeRegister> finddata = collegeRegisterRepo.findAll(sId);
			collegeRegisterRepo.deleteAll(finddata);
			registerRepo.deleteById(sId);
			//PaymentDepositrepo.deletedata(findById.getRegister().getRegisterId());
		
		
		return 1;
		}
		return 0;
		
		
	}
//	
//	   @GetMapping("{filename:.+}")
//	    @ResponseBody
//	    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
//		   System.out.println("filname is:"+filename);
//	        Register file = registerRepo.download(filename);
//	        Path path = file.getFile().to
//	                       
//
//	        return ResponseEntity.ok()
//	                             .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
//	                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//	                             .body(file);
//	    }
	
	 @GetMapping("/files/{fileId}")
   public void downloadFile(@PathVariable Integer fileId,HttpServletResponse response) throws IOException {
		System.out.println("Inside Download File--------->>"+fileId);
		Register getfilebyId = registerRepo.getfilebyId(fileId);
		System.out.println("the filedata is:"+getfilebyId);
		
		CommonFileUpload.downloadFile(response,getfilebyId.getFile());
	}
	  
}
