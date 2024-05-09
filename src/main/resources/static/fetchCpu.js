let gaugeChartCpu;
let lineChartCpu;

let processorUsage;
let lineCpuUsage;

let cpuUsedColor = '#EDEDED';
let cpuFreeColor = '#EDEDED';

// Fetch data from the Cpu API
function fetchCpu() {
    fetch('/api/v1/Cpu')
        .then((response) => response.json())
        .then((cpuArray) => {

            console.log("Fetched CPU data:", cpuArray); // Examine the raw data

            
            // Sort the array by 'dateTime' property in ascending order
            cpuArray.sort((a, b) => new Date(a.dateTime) - new Date(b.dateTime));

            // Fetch the latest CPU record (last item after sorting)
            const latestCpu = cpuArray[cpuArray.length - 1];
            
            // Get the Cpu usage percentage
            processorUsage = latestCpu.processorUsage;
            lineCpuUsage = latestCpu.processorUsage;

            // Clear and update cpu list elements
            const cpuListElements = document.querySelectorAll('.cpuList');
            cpuListElements.forEach((cpuListElement) => {
                // Clear existing content
                cpuListElement.innerHTML = '';
                // Create a new list item for the latest CPU
                const listCpuItem = document.createElement('li');
                listCpuItem.style.whiteSpace = 'pre-line';
                listCpuItem.textContent = 
                    // `CPU ID:    ${latestCpu.id}\n` + 
                    `Collection cycles:  5sec\n` +
                    `ProcessorType:     ${latestCpu.processorType} \n` +
                    `ProcessorSpeed:    ${latestCpu.processorSpeed} GHZ\n` + 
                    `ProcessorUsage:    ${latestCpu.processorUsage} %\n` +
                    `ProcessorUptime:   ${latestCpu.formattedUptime} \n` +
                    // `ProcessorUptimeInSec: ${latestCpu.processorUptime}\n` +
                    `Count: ${latestCpu.count}`;

                // Append the new list item to the list element
                cpuListElement.appendChild(listCpuItem);
            });

            // Change the used color based on cpu usage
            if (processorUsage >= 0 && processorUsage <= 50) {
                // Set color green
                cpuUsedColor = '#00FF00';
            } else if (processorUsage > 50 &&  processorUsage <=80){
                // Set color yellow
                cpuUsedColor = '#FFFF00';
            } else if (processorUsage > 80 &&  processorUsage <=99){
                // Set color red
                cpuUsedColor = '#FF6384'
            } else {
                console.error('CpuPercentage out of range: ', processorUsage);
                return;
            }

            // Find the gauge chart element
            const cpuGaugeChartElement = document.getElementById('gaugeChartCpu');
            // Check gauge chart element
            if (cpuGaugeChartElement){
                // Get the canvas context
                const ctx1Cpu = cpuGaugeChartElement.getContext('2d');
                if (!gaugeChartCpu){
                    // Define data for the gauge chart
                    const cpuChartData = {
                        datasets: [
                            {
                                data: [processorUsage, 100 - processorUsage],
                                backgroundColor: [cpuUsedColor,cpuFreeColor],
                                borderWidth: 0,
                            },
                        ],
                    };

                    // Create the gauge chart if it doesn't exist
                    gaugeChartCpu = new Chart(ctx1Cpu, {
                        type: 'doughnut',
                        data: cpuChartData,
                        options: {
                            responsive: true,
                            maintainAspectRatio: true,
                            aspectRatio:4,
                            rotation: -90, 
                            circumference: 180, 
                            cutout: '30%', 
                            plugins: {
                                legend: { display: false },
                                title: { display: true, text: "Cpu Usage Gauge" },
                            },
                        },
                    });
                } else {
                    // Update the existing color and chart
                    gaugeChartCpu.data.datasets[0].data = [processorUsage, 100 - processorUsage];
                    gaugeChartCpu.data.datasets[0].backgroundColor = [cpuUsedColor, cpuFreeColor];
                    gaugeChartCpu.update(); // Trigger re-rendering
                }
            } else {
                console.error('Gauge chart element not found');
            }

            // Find the line chart element
            const cpuLineChartElement = document.getElementById('lineChartCpu');
            if (cpuLineChartElement){
                // Get the canvns element
                const ctx2Cpu = cpuLineChartElement.getContext('2d');
                if (!lineChartCpu) {
                    // Generate labels:
                    const labels = [];

                    for (let i = 1; i<= 10; i++){
                        labels.push(`${i}`);
                    };

                    // Define data for the line chart
                    const cpuLineData = {
                        labels: labels,
                        datasets: [
                            {
                                label: "Cpu Usage",
                                data: [lineCpuUsage],
                                fill: false,
                                borderColor: 'rgb(75,192,192)',
                                tension: 0.1
                            },
                        ],
                    };

                    // Create the line chart if it doesn't exist 
                    lineChartCpu = new Chart (ctx2Cpu, {
                        type: 'line',
                        data: cpuLineData,
                        options: {
                            responsive: true,
                            maintainAspectRatio: true,
                            scales: {
                                y: {
                                    beginAtZero: true,
                                    suggestedMax: 100,
                                },
                            },
                        },
                    });
                } else {
                    // Add new data to the chart
                    lineChartCpu.data.datasets[0].data.push(lineCpuUsage);
                    // Remove oldest data if more than 10 items
                    if (lineChartCpu.data.datasets[0].data.length > 10){
                        // Remove the first item
                        lineChartCpu.data.datasets[0].data.shift();
                    }
                    lineChartCpu.update();
                }
            } else {
                console.error("Line chart element not found.");
            }
        })
        .catch(error => console.error('Error fetching Cpu data:', error));
}