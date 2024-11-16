package Controller;
import Enums.Locations;
import Model.*;
import Service.OfferService;

import java.util.List;

/**
 * Controller for the offers
 */
public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    /**
     * Contructs the controller with the specified service addOffer
     * @param newPrice
     * @param products
     */
    public void add( int newPrice, List<Product> products) {
        offerService.addOffer( newPrice, products);
    }//TODO: user input

    public List<Offer> filterOffersByProduct(List<Offer> offers, Product product) {
        return offerService.filterOffersByProduct(offers, product);
    }
}
