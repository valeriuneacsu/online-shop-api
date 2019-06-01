package org.fasttrackit.onlineshopapi;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.ProductService;
import org.fasttrackit.onlineshopapi.transfer.CreateProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductService productService;

	@Test
	public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {

        createProduct();

    }

    private Product createProduct() {
        CreateProductRequest request = new CreateProductRequest();
        request.setName("Mazare");
        request.setPrice(99.99);
        request.setQuantity(10);

        Product createdProduct = productService.createProduct(request);

        assertThat (createdProduct, notNullValue());
        assertThat (createdProduct.getId(), greaterThan(0L));
        assertThat (createdProduct.getName(), is(request.getName()));
        assertThat (createdProduct.getPrice(), is(request.getPrice()));
        assertThat (createdProduct.getQuantity(), is(request.getQuantity()));

        return createdProduct;
    }

    @Test (expected = TransactionSystemException.class)
	public void testCreateProduct_whenMissingMandatoryProperties_thenThrowException(){
        CreateProductRequest request = new CreateProductRequest();
        productService.createProduct(request);
    }

    @Test
    public void testGetProduct_whenExistingId_thenReturnProduct() throws ResourceNotFoundException {
	    Product createdProduct = createProduct();

	    Product product = productService.getProduct(createdProduct.getId());

	    assertThat(product, notNullValue());
	    assertThat(product.getId(), is(createdProduct.getId()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProduct_whenNonExistingId_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
	   productService.getProduct(9999L);
    }

}
