package sg.edu.nus.iss.paf_mocktest1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.paf_mocktest1.exception.TransferException;
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
        transaction.setAccounts(listOfAllAccounts);

		// model.addAttribute(ATTR_ITEM, new Item());
		model.addAttribute(ATTR_TRANSACTION, transaction);

		return "index";
	}

	@PostMapping(path="/transfer")
    public String postTransfer(@ModelAttribute Transaction transaction, Model m, HttpSession session) throws TransferException{

		/*
		 * 
		 * Perform checks
		 * 
		 */
		if (transaction.getToAccount() == "" || transaction.getFromAccount() == "" ){
            throw new TransferException("Please provide both accounts");
        }

		if (transaction.getToAccount().equals(transaction.getFromAccount())){
            throw new TransferException("Cannot transfer to the same account");
        }

		if (transaction.getAmount() == null || transaction.getAmount() <= 0) {
			throw new TransferException("Amount cannot be 0 or negative");
		} else if (transaction.getAmount().equals("")) {
			throw new TransferException("Amount field cannot be empty");
		}

		if (transaction.getAmount() < 10 ){
            throw new TransferException("Minimum transfer amount is $10");
        }

		Account account = fundSvc.getAccountById(transaction.getFromAccount());
		if(account.getBalance() < transaction.getAmount()){
			throw new TransferException("Insufficient balance in account");
		}

		// m.addAttribute(ATTR_TRANSACTION, transaction);

       return "view2";

    }


	@ExceptionHandler(TransferException.class)
    public String handleTransferException(TransferException e, Model m, HttpSession session){
        Transaction transaction = getTransaction(session);
        List<Account> listOfAllAccounts = fundSvc.getAllAccounts();
        transaction.setAccounts(listOfAllAccounts);
		m.addAttribute("errorMessage", e.getMessage());
		m.addAttribute(ATTR_TRANSACTION, transaction);
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
