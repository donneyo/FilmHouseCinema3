		
	const selectElement = (element) => document.querySelector(element);
	//Open and close nav on click
	selectElement('.menu-icons'), addEventListener('click', () => {
	  selectElement('nav').classList.toggle('active');
	})

	AOS.init({
	  easing: 'ease',
	  duration: 1800,
	  once: true
	});

