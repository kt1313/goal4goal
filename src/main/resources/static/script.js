const apiKey="DKaGg.btvWFA:KUj5XkMzreuJIPKr";
const channel="%5Bproduct%Aably-coindesk%2Fbitcoin%5Dbitcoin%3Ausd";
const url ='https://rest.ably.io/sse?v=1.1&key=${apiKey}&channels=${channels}';
const eventSource=new EventSource(url);
const enevntList=document.querySelector('ul#events');
function addEvent(text){
    const newElement=document.createElement("li");
    newElement.textContent=text;
    eventList.appendChild(newElement);
}
eventSource.onopen=()=>{
    addEvent('Connected and subscribed to channels: ${channels}');
}
eventSource.onmessage=(event)=>{
    const message=JSON.parse(event.data);
    addEvent('Message: ${message.name}-${message.data}');
}
eventSource.onerror=(error)=>{
    if(error.data){
        addEvent('Error: ${error.data}');

    }else {
        addEvent('Error connecting to Ably');
    }
}
