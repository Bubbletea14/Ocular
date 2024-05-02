        //Fetch data from the processes API 
        function fetchProcess () {
            fetch('/api/v1/Processes')
                .then(response => response.json())
                .then(processes => {
                    //Handle the response data for processes
                    const processListElements = document.querySelectorAll('.processList');
                    processListElements.forEach(processListElement => {
                        processListElement.innerHTML = '';
                        processes.forEach(process => {
                            const listProcessItem = document.createElement('li');
                            listProcessItem.textContent = `PID: ${process.pid}, Name: ${process.name}`;
                            processListElement.appendChild(listProcessItem);
                        }); 
                    });
                })
                .catch(error => console.error('Error fetching processes:', error));
            }  