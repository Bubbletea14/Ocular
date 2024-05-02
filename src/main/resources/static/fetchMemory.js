function fetchMemory() {
    fetch('/api/v1/Memory')
        .then((response) => response.json())
        .then((memoryArray) => {
            // Sort the array by 'dateTime' property in ascending order
            memoryArray.sort((a, b) => new Date(a.dateTime) - new Date(b.dateTime));

            // Fetch the latest Memory record
            const latestMemory = memoryArray[memoryArray.length - 1];

            // Memory Usage percentage for the chart
            const memoryUsagePercentage = latestMemory.memoryUsagePercentage;

            // Clear and update memory list elements
            const memoryListElements = document.querySelectorAll('.memoryList');
            memoryListElements.forEach((memoryListElement) => {
                memoryListElement.innerHTML = ''; // Clear existing content

                // Create a new list item for the latest Memory
                const listMemoryItem = document.createElement('li');
                listMemoryItem.style.whiteSpace = 'pre-line';
                listMemoryItem.textContent =
                    `Memory ID: ${latestMemory.id}\n` +
                    `Total Memory: ${latestMemory.totalMemory}\n` +
                    `Free Memory: ${latestMemory.freeMemory}\n` +
                    `Used Memory: ${latestMemory.usedMemory}\n` +
                    `Memory Speed: ${latestMemory.memorySpeed}\n` +
                    `Memory Usage Percentage: ${latestMemory.memoryUsagePercentage}`;

                // Append the new list item to the list element
                memoryListElement.appendChild(listMemoryItem);
            });

            // Data for the chart
            const chartData = {
                datasets: [
                    {
                        data: [memoryUsagePercentage, 100 - memoryUsagePercentage], // Representing the gauge
                        backgroundColor: ['#FF6384', '#EDEDED'], // Colors for used and unused segments
                        borderWidth: 0, // No border
                    },
                ],
            };

            const ctx = document.getElementById('gaugeChart').getContext('2d');
            
            // Create a doughnut chart to mimic a gauge
            const gaugeChart = new Chart(ctx, {
                type: 'doughnut',
                data: chartData,
                options: {
                    rotation: -90, // Start angle to create a semicircle
                    circumference: 180, // Creates a semicircle
                    cutout: '90%', // Inner cutout to make it appear like a gauge
                    plugins: {
                        legend: {
                            display: false, // Hide the legend
                        },
                        title: {
                            display: true,
                            text: "Memory Usage Gauge",
                        },
                    },
                },
            });

            // Custom drawing logic for the needle
            // Chart.register({
            //     afterDraw: function (chart) {
            //         // Calculate the angle for the needle
            //         const angle = -Math.PI / 2 + (Math.PI * memoryUsagePercentage) / 100;

            //         const ctx = chart.chart.ctx;
            //         const centerX = (chart.chartArea.left + chart.chartArea.right) / 2; // Center X
            //         const centerY = chart.chartArea.bottom; // Center Y

            //         ctx.save();
            //         ctx.translate(centerX, centerY); // Move to the center of the semicircle
            //         ctx.rotate(angle); // Rotate to the calculated angle
            //         ctx.strokeStyle = '#000000'; // Needle color
            //         ctx.lineWidth = 2; // Needle thickness

            //         ctx.beginPath();
            //         ctx.moveTo(0, 0); // Start at the center
            //         ctx.lineTo(0, -80); // Needle length (upwards)
            //         ctx.stroke();
            //         ctx.restore();
            //     },
            // });

        })
        .catch((error) => console.error('Error fetching memory data:', error));
}

