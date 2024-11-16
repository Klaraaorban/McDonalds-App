package Service;
import Enums.Locations;
import Model.*;
import Repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class OfferService {
    private final IRepository<Offer> offerRepository;

    /**
     * @param repository
     */
    public OfferService(IRepository repository) {
        this.offerRepository = repository;
    }

    /**
     * @param newPrice
     * @param products
     */
    public void addOffer( int newPrice, List<Product> products) {
        int price = 0;
        for (Product product : products) {
            price += product.getProductPrice();
        }
        Offer offer = new Offer(price, newPrice, products);
        offerRepository.create(offer);
    }

    /**
     * @param id
     * @return
     */
    public Offer getOffer(int id) {
        return offerRepository.read(id);
    }

    /**
     * @param id
     */
    public void deleteOffer(int id) {
        offerRepository.delete(id);
    }
//a expirat termenul

    //TODOL filterOffersByProduct
    public List<Offer> filterOffersByProduct(List<Offer> offers, Product product) {
        return offers.stream()
                .filter(offer -> offer.getProducts().contains(product))
                .collect(Collectors.toList());
    }

}
