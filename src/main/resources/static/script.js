async function createClientElement(client) {
    return `<span class="text">${client.name} ${client.surname} (${client.login})</span>
            <div class="buttons">
                <button class="button edit">Edit</button>
                <button class="button delete">Delete</button>
            </div>`;
}

async function createBookingElement(booking) {
    const userLogin = await fetchLogin(booking.userId);
    const formattedFrom = formatDate(booking.reservationFrom);
    const formattedUntil = formatDate(booking.reservationUntil);
    return `<span class="text">Room ${booking.roomNumber}, from ${formattedFrom} to ${formattedUntil}, User: ${userLogin}</span>
            <div class="buttons">
                <button class="button edit">Edit</button>
                <button class="button delete">Delete</button>
            </div>`;
}

async function createRoomElement(room) {
    return `<span class="text">Room № ${room.roomNumber}</span>
            <div class="buttons">
                <button class="button edit">Edit</button>
                <button class="button delete">Delete</button>
            </div>`;
}

function formatDate(dateString) {
    const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
    return new Date(dateString).toLocaleDateString('en-GB', options);
}

async function fetchData(url, elementId, createElement) {
    try {
        const response = await fetch(url);
        const data = await response.json();
        console.log(`Data from ${url}:`, data);
        const container = document.getElementById(elementId);
        for (const item of data) {
            const div = document.createElement('div');
            div.innerHTML = await createElement(item);
            container.appendChild(div);
        }
    } catch (error) {
        console.error(`Error fetching data from ${url}:`, error);
    }
}

async function fetchLogin(userId) {
    try {
        const response = await fetch(`/admin/loginById/${userId}`);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return await response.text();
    } catch (error) {
        console.error('Error fetching login:', error);
        return 'Unknown User';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    fetchData('/admin/clients', 'clients', createClientElement);
    fetchData('/admin/bookings', 'bookings', createBookingElement);
    fetchData('/admin/rooms', 'rooms', createRoomElement);
});
