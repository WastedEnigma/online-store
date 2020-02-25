const clearToken = () => {
    if (window.localStorage.getItem('username')) {
        window.localStorage.setItem('onlineStoreToken', '');
    }
};

$('#log-out-btn').click(clearToken);
