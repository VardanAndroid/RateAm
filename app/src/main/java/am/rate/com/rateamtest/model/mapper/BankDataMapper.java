package am.rate.com.rateamtest.model.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import am.rate.com.rateamtest.model.entity.AddressEntity;
import am.rate.com.rateamtest.model.entity.BankEntity;
import am.rate.com.rateamtest.model.entity.BranchEntity;
import am.rate.com.rateamtest.model.entity.CurrencyEntity;
import am.rate.com.rateamtest.model.entity.RateEntity;
import am.rate.com.rateamtest.model.entity.TitleEntity;
import am.rate.com.rateamtest.view.model.Address;
import am.rate.com.rateamtest.view.model.Bank;
import am.rate.com.rateamtest.view.model.Branch;
import am.rate.com.rateamtest.view.model.Currency;
import am.rate.com.rateamtest.view.model.Rate;
import am.rate.com.rateamtest.view.model.Title;

public class BankDataMapper {

    @Inject
    public BankDataMapper() {
    }

    public List<Bank> getBanks(Map<String, BankEntity> entityMap) {
        List<Bank> banks = new ArrayList<>();

        Set<String> ids = entityMap.keySet();
        for (String id : ids) {
            BankEntity bankEntity = entityMap.get(id);
            Bank bank = new Bank(id);
            bank.setName(bankEntity.getName());
            bank.setLogo(bankEntity.getLogo());
            Map<String, CurrencyEntity> map = bankEntity.getRates();
            Map<String, Currency> currencyMap = findRateMap(map);
            bank.setCurrencyMap(currencyMap);
            banks.add(bank);
        }
        return banks;
    }

    public List<Branch> getBranches(Map<String, BranchEntity> branchEntityMap) {
        List<Branch> branches = new ArrayList<>();
        Set<String> ids = branchEntityMap.keySet();
        for (String id :
                ids) {
            Branch branch = new Branch(id);
            BranchEntity branchEntity = branchEntityMap.get(id);
            TitleEntity titleEntity = branchEntity.getTitleEntity();
            Title title = new Title(titleEntity.getTitleAm(), titleEntity.getTitleRu(),
                    titleEntity.getTitleEn());
            branch.setTitle(title);
            branch.setHead(branchEntity.isHead());
            AddressEntity addressEntity = branchEntity.getAddressEntity();
            Address address = new Address(addressEntity.getAddressEn(),
                    addressEntity.getAddressRu(), addressEntity.getAddressAm());
            branch.setAddress(address);
            branch.setContact(branchEntity.getContacts());
            if (branchEntity.getWorkingDayEntities() != null && branchEntity.getWorkingDayEntities().size() > 0) {
                branch.setWorkingHours(branchEntity.getWorkingDayEntities().get(0).getHours());
            }
            branches.add(branch);
        }
        return branches;

    }

    private Map<String, Currency> findRateMap(Map<String, CurrencyEntity> map) {
        Map<String, Currency> currencyMap = new HashMap<>();
        Set<String> keySet = map.keySet();
        for (String key :
                keySet) {
            CurrencyEntity currencyEntity = map.get(key);
            RateEntity cashEntity = currencyEntity.getCashRate();
            RateEntity nonCashEntity = currencyEntity.getNonCashRate();
            Rate cash = null;
            Rate nonCash = null;
            if (cashEntity != null) {
                cash = new Rate(cashEntity.getBuy(), cashEntity.getSell());
            }
            if (nonCashEntity != null) {
                nonCash = new Rate(nonCashEntity.getBuy(), nonCashEntity.getSell());
            }

            Currency currency = new Currency(cash, nonCash);
            currencyMap.put(key, currency);
        }
        return currencyMap;
    }
}
