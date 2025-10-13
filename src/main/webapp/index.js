const navMenu = document.getElementById('navMenu')

navMenu.addEventListener('click', (event) => {
    if (event.target.classList.contains('nav-item')) {
        const url = event.target.getAttribute('data-url')
        const contentFrame = document.getElementById('contentFrame')
        contentFrame.src = url
    }
})

