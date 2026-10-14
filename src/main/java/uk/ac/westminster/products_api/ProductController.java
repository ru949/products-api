
package uk.ac.westminster.products_api;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // Connect ProductController to ProductService
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 2. Add a new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // 3. Get a product by ID
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getProductById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + id));
    }

    // 4. Delete a product by ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }

    // 5. Search products by name
    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.findByName(name);
    }
}
