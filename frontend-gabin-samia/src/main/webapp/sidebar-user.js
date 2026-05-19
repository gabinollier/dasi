function chargerUtilisateurSidebar() {
    fetch('ActionServlet?todo=get-utilisateur-session', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            if (!data || !data.connected) {
                return;
            }

            const nameEl = document.getElementById('sidebar-user-name');
            const gradeEl = document.getElementById('sidebar-user-grade');

            if (nameEl) {
                const fullName = `${data.prenom || ''} ${data.nom || ''}`.trim();
                nameEl.textContent = fullName || 'Utilisateur';
            }

            if (gradeEl) {
                if (data.type === 'eleve' && data.classe !== undefined && data.classe !== null && data.classe !== '') {
                    gradeEl.textContent = `${data.classe}eme`;
                } else if (data.type === 'intervenant') {
                    gradeEl.textContent = 'Intervenant';
                }
            }
        })
        .catch(() => {});
}

document.addEventListener('DOMContentLoaded', () => {
    chargerUtilisateurSidebar();
    const logoutLinks = document.querySelectorAll('.sidebar-logout');
    logoutLinks.forEach(link => {
        link.addEventListener('click', event => {
            event.preventDefault();
            fetch('ActionServlet?todo=deconnecter', {
                method: 'POST'
            })
                .then(() => {
                    window.location.href = './connexion.html';
                })
                .catch(() => {
                    window.location.href = './connexion.html';
                });
        });
    });
});
