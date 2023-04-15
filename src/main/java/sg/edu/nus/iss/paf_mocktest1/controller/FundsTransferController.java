package sg.edu.nus.iss.paf_mocktest1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.paf_mocktest1.models.Account;
import sg.edu.nus.iss.paf_mocktest1.models.Transaction;
import sg.edu.nus.iss.paf_mocktest1.service.FundsTransferService;

@Controller
@RequestMapping
public class FundsTransferController {
    
    public static final String ATTR_TRANSACTION = "transaction";
    
	@Autowired
	private FundsTransferService fundSvc;

	//start page 
	@GetMapping(path={"/", "/index.html"})
	public String getIndex(Model model, HttpSession sess) {

		Transaction transaction = getTransaction(sess);
        List<Account> listOfAllAccounts = fundSvc.getAllAccounts();

		// model.addAttribute(ATTR_ITEM, new Item());
		model.addAttribute(ATTR_TRANSACTION, transaction);

		return "index";
	}

    private Transaction getTransaction(HttpSession sess) {
		Transaction transaction = (Transaction)sess.getAttribute(ATTR_TRANSACTION);
		if (null == transaction) {
			transaction = new Transaction();
			sess.setAttribute(ATTR_TRANSACTION, transaction);
		}
		return transaction;
	}

}
