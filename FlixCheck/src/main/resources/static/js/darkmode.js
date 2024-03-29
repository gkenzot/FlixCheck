document.addEventListener('DOMContentLoaded', function () {
    var darkModeBtn = document.getElementById('darkModeSwitch');
    var darkModeCookie = getCookie('darkMode');

    if (darkModeCookie === 'true') {
        enableDarkMode();
        darkModeBtn.checked = true;
    }

    darkModeBtn.addEventListener('click', function () {
        toggleDarkMode();
    });

    function toggleDarkMode() {
        if (darkModeCookie === 'true') {
            darkModeCookie = 'false';
            disableDarkMode();
        } else {
            darkModeCookie = 'true';
            enableDarkMode();
        }

        updateDarkModeOnServer(darkModeCookie);
    }

    function getCookie(name) {
        var match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
        if (match) return match[2];
        return null;
    }

    function updateDarkModeOnServer(newMode) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/dark-mode-toggle?currentMode=' + newMode, true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.cookie = 'darkMode=' + newMode;
            }
        };

        xhr.send();
    }

    function enableDarkMode() {
        document.body.classList.add('dark-mode');
        var mode1 = document.getElementsByClassName('dark-mode-bg1');
        for (var i = 0; i < mode1.length; i++) {
            mode1[i].classList.add('bg-black');
            mode1[i].classList.add('text-white');
        }

        var mode2 = document.getElementsByClassName('dark-mode-bg2');   
        for (var i = 0; i < mode2.length; i++) {
            mode2[i].classList.add('bg-dark');
            mode2[i].classList.add('text-white');
        }
    }

    function disableDarkMode() {
        document.body.classList.remove('dark-mode');
        var mode1 = document.getElementsByClassName('dark-mode-bg1');
        for (var i = 0; i < mode1.length; i++) {
            mode1[i].classList.remove('bg-black');
            mode1[i].classList.remove('text-white');
        }

        var mode2 = document.getElementsByClassName('dark-mode-bg2');
        for (var i = 0; i < mode2.length; i++) {
            mode2[i].classList.remove('bg-dark');
            mode2[i].classList.remove('text-white');
        }
    }
});
