document.addEventListener("DOMContentLoaded", () => {
  // Add Hari Raya banner
  const header = document.querySelector(".header")
  const banner = document.createElement("div")
  banner.className = "hari-raya-banner"
  banner.innerHTML = `
        <h1>Selamat Hari Raya Aidilfitri</h1>
        <p>Celebrate this festive season with our special Raya promotions. Enjoy exclusive discounts on our premium products!</p>
        <div class="ketupat-decoration ketupat-1"></div>
        <div class="ketupat-decoration ketupat-2"></div>
    `
  header.after(banner)

  // Add decorative elements
  const body = document.querySelector("body")
  const moon = document.createElement("div")
  moon.className = "decoration moon"
  body.appendChild(moon)

  const star1 = document.createElement("div")
  star1.className = "decoration star star-1"
  body.appendChild(star1)

  const star2 = document.createElement("div")
  star2.className = "decoration star star-2"
  body.appendChild(star2)

  const star3 = document.createElement("div")
  star3.className = "decoration star star-3"
  body.appendChild(star3)

  // Transform product cards
  const innerContainer = document.querySelector(".inner-container")
  const productDivs = document.querySelectorAll(".inner-container > div")

  // Create a wrapper for products
  const productsContainer = document.createElement("div")
  productsContainer.className = "products-container"

  // Move the inner container into the products container
  if (innerContainer) {
    innerContainer.parentNode.insertBefore(productsContainer, innerContainer)
    productsContainer.appendChild(innerContainer)
  }

  productDivs.forEach((div) => {
    div.className = "product-card"

    // Add discount tag
    const discountTag = document.createElement("div")
    discountTag.className = "discount-tag"

    // Restructure product info
    const h3 = div.querySelector("h3")
    const img = div.querySelector("img")

    // Ensure image displays properly
    if (img) {
      img.removeAttribute("width") // Remove fixed width attribute
      img.style.maxWidth = "100%"
    }

    const form = div.querySelector("form")
    const originalPrice = div.querySelector("p:nth-of-type(1)")
    const promoPrice = div.querySelector("p:nth-of-type(2)")

    // Extract discount percentage from the product
    if (originalPrice && promoPrice) {
      const originalPriceText = originalPrice.textContent
      const promoPriceText = promoPrice.textContent

      const originalPriceValue = Number.parseFloat(originalPriceText.replace("Original Price: RM ", ""))
      const promoPriceValue = Number.parseFloat(promoPriceText.replace("Promo Price: RM ", ""))

      if (!isNaN(originalPriceValue) && !isNaN(promoPriceValue)) {
        const discountPercentage = Math.round((1 - promoPriceValue / originalPriceValue) * 100)
        discountTag.textContent = `-${discountPercentage}%`
        div.appendChild(discountTag)
      }
    }

    // Add promo badge
    const promoBadge = document.createElement("div")
    promoBadge.className = "promo-badge"
    promoBadge.textContent = "RAYA SPECIAL"
    div.appendChild(promoBadge)

    // Clear the div and rebuild it
    div.innerHTML = ""
    div.appendChild(discountTag)
    div.appendChild(promoBadge)
    div.appendChild(img)

    const productInfo = document.createElement("div")
    productInfo.className = "product-info"

    if (h3) productInfo.appendChild(h3)

    const priceContainer = document.createElement("div")
    priceContainer.className = "price-container"

    if (originalPrice) {
      originalPrice.className = "original-price"
      priceContainer.appendChild(originalPrice)
    }

    if (promoPrice) {
      promoPrice.className = "promo-price"
      priceContainer.appendChild(promoPrice)
    }

    productInfo.appendChild(priceContainer)

    if (form) productInfo.appendChild(form)

    div.appendChild(productInfo)
  })

  // Handle empty state
  if (innerContainer && !innerContainer.children.length) {
    const emptyState = document.createElement("div")
    emptyState.className = "empty-state"
    emptyState.innerHTML = `
            <p>No products available in this promotion.</p>
            <p>Please check back later for our Hari Raya special offers!</p>
        `
    innerContainer.appendChild(emptyState)
  }

  // Add footer
  const footer = document.createElement("footer")
  footer.className = "footer"
  footer.innerHTML = `
        <div class="footer-content">
            <div class="footer-section">
                <h3>About Us</h3>
                <p>GLOWY DAYS offers premium products to enhance your beauty and lifestyle. Our Hari Raya collection brings special offers to celebrate the festive season.</p>
            </div>
            <div class="footer-section">
                <h3>Quick Links</h3>
                <a href="#">Home</a>
                <a href="#">Products</a>
                <a href="#">About Us</a>
                <a href="#">Contact Us</a>
            </div>
            <div class="footer-section">
                <h3>Contact Info</h3>
                <p><i class="fa-solid fa-location-dot"></i> 123 Jalan Raya, Kuala Lumpur</p>
                <p><i class="fa-solid fa-phone"></i> +60 12-345 6789</p>
                <p><i class="fa-solid fa-envelope"></i> info@glowydays.com</p>
                <div class="social-icons">
                    <a href="#"><i class="fa-brands fa-facebook-f"></i></a>
                    <a href="#"><i class="fa-brands fa-instagram"></i></a>
                    <a href="#"><i class="fa-brands fa-twitter"></i></a>
                </div>
            </div>
        </div>
        <div class="copyright">
            <p>&copy; 2023 GLOWY DAYS. All Rights Reserved.</p>
        </div>
    `
  document.body.appendChild(footer)

  // Search functionality
  const searchBox = document.getElementById("search-box")
  if (searchBox) {
    searchBox.addEventListener("keyup", (e) => {
      if (e.key === "Enter") {
        const query = searchBox.value.trim().toLowerCase()
        if (query) {
          const productCards = document.querySelectorAll(".product-card")
          let found = false

          productCards.forEach((card) => {
            const productName = card.querySelector("h3").textContent.toLowerCase()
            if (productName.includes(query)) {
              card.style.display = "block"
              found = true
            } else {
              card.style.display = "none"
            }
          })

          if (!found) {
            alert("No products found matching your search.")
            productCards.forEach((card) => {
              card.style.display = "block"
            })
          }
        }
      }
    })
  }
})