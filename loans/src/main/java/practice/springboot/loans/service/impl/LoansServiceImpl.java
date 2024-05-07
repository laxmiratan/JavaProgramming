package practice.springboot.loans.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import practice.springboot.loans.constants.LoansConstants;
import practice.springboot.loans.entity.Loans;
import practice.springboot.loans.exception.LoanAlreadyExistException;
import practice.springboot.loans.repository.LoansRepository;
import practice.springboot.loans.service.ILoansService;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;


    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByLoanNumber(mobileNumber);

        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistException("Loan already registered with given mobileNumber "+mobileNumber);
        }

        loansRepository.save(createNewLoans(mobileNumber));
    }

    private Loans createNewLoans(String mobileNumber){
        Loans newLoan = new Loans();

        long randomNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }
}
