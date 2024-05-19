/*!
* Start Bootstrap - Simple Sidebar v6.0.6 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

// window.addEventListener('DOMContentLoaded', event => {
//     const sidebarToggle = document.querySelector('#sidebarToggle');
//     if (sidebarToggle) {
//         sidebarToggle.addEventListener('click', event => {
//             document.getElementById("toggleIcon").src = 'https://www.svgrepo.com/show/533620/arrow-sm-left.svg';
//         });
//     }
// });


// window.addEventListener('DOMContentLoaded', event => {
//     const sidebarToggle = document.body.querySelector('#sidebarToggle');
//     if (sidebarToggle) {
//         sidebarToggle.addEventListener('click', event => {
//             event.preventDefault();
//             const sidebar = document.body.querySelector('#sidebar');
//             const arrowIcon = sidebarToggle.querySelector('svg');
//
//             document.body.classList.toggle('sb-sidenav-toggled');
//
//             if (document.body.classList.contains('sb-sidenav-toggled')) {
//                 // 사이드바가 열려있을 때
//                 arrowIcon.classList.remove('bi-arrow-down');
//                 arrowIcon.classList.add('bi-arrow-left');
//             } else {
//                 // 사이드바가 닫혀있을 때
//                 arrowIcon.classList.remove('bi-arrow-left');
//                 arrowIcon.classList.add('bi-arrow-down');
//             }
//         });
//     }
// });

// window.addEventListener('DOMContentLoaded', event => {
//     const sidebarToggle = document.querySelector('#sidebarToggle');
//     if (sidebarToggle) {
//         sidebarToggle.addEventListener('click', event => {
//             const arrowIcon = sidebarToggle.querySelector('svg');
//             arrowIcon.style.transform = arrowIcon.style.transform === 'rotate(180deg)' ? 'rotate(0deg)' : 'rotate(180deg)';
//         });
//     }
// });