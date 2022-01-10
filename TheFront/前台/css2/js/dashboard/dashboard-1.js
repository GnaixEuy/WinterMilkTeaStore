(function($) {
    /* "use strict" */


 var dzChartlist = function(){
	let draw = Chart.controllers.line.__super__.draw; //draw shadow
	var screenWidth = $(window).width();
	var donutChart1 = function(){
		$("span.donut1").peity("donut", {
			width: "92",
			height: "92"
		});
	}
	var radialBar = function(){
		 var options = {
          series: [85],
          chart: {
          height: 300,
          type: 'radialBar',
          offsetY: -10
        },
        plotOptions: {
          radialBar: {
            startAngle: -135,
            endAngle: 135,
            dataLabels: {
              name: {
                fontSize: '16px',
                color: undefined,
                offsetY: 120
              },
              value: {
                offsetY: 0,
                fontSize: '34px',
                color: 'black',
                formatter: function (val) {
                  return val + "%";
                }
              }
            }
          }
        },
        fill: {
          type: 'gradient',
		  colors:'#EA7A9A',
          gradient: {
              shade: 'dark',
              shadeIntensity: 0.15,
              inverseColors: false,
              opacityFrom: 1,
              opacityTo: 1,
              stops: [0, 50, 65, 91]
          },
        },
        stroke: {
		  colors:'#EA7A9A'
        },
        labels: [''],
        };

        var chart = new ApexCharts(document.querySelector("#radialBar"), options);
        chart.render();
	}
	var radialBar2 = function(){
		 var options = {
          series: [40],
          chart: {
          height: 300,
          type: 'radialBar',
          offsetY: -10
        },
        plotOptions: {
          radialBar: {
            startAngle: -135,
            endAngle: 135,
            dataLabels: {
              name: {
                fontSize: '16px',
                color: undefined,
                offsetY: 120
              },
              value: {
                offsetY: 0,
                fontSize: '34px',
                color: 'black',
                formatter: function (val) {
                  return val + "%";
                }
              }
            }
          }
        },
        fill: {
          type: 'gradient',
		  colors:'#EA7A9A',
          gradient: {
              shade: 'dark',
              shadeIntensity: 0.15,
              inverseColors: false,
              opacityFrom: 1,
              opacityTo: 1,
              stops: [0, 50, 65, 91]
          },
        },
        stroke: {
		  colors:'#EA7A9A'
        },
        labels: [''],
        };

        var chart = new ApexCharts(document.querySelector("#radialBar2"), options);
        chart.render();
	}
	var radialBar3 = function(){
		 var options = {
          series: [55],
          chart: {
          height: 300,
          type: 'radialBar',
          offsetY: -10
        },
        plotOptions: {
          radialBar: {
            startAngle: -135,
            endAngle: 135,
            dataLabels: {
              name: {
                fontSize: '16px',
                color: undefined,
                offsetY: 120
              },
              value: {
                offsetY: 0,
                fontSize: '34px',
                color: 'black',
                formatter: function (val) {
                  return val + "%";
                }
              }
            }
          }
        },
        fill: {
          type: 'gradient',
		  colors:'#EA7A9A',
          gradient: {
              shade: 'dark',
              shadeIntensity: 0.15,
              inverseColors: false,
              opacityFrom: 1,
              opacityTo: 1,
              stops: [0, 50, 65, 91]
          },
        },
        stroke: {
		  colors:'#EA7A9A'
        },
        labels: [''],
        };

        var chart = new ApexCharts(document.querySelector("#radialBar3"), options);
        chart.render();
	}
	
	var chartBar = function(){
		var activity = document.getElementById("activityLine");
		var inputs = {
			min: 20,
			max: 80,
			count: 8,
			decimals: 2,
			continuity: 1
		};
		if (activity !== null) {
			var activityData = [{
					first: [50, 75, 34, 55, 25, 70, 30, 80, 30, 90, 25, 65]
				},
				{
					first: [50, 35, 10, 45, 40, 50, 60, 35, 10, 70, 34, 35]
				},
				{
					first: [20, 35, 70, 45, 40, 35, 30, 35, 10, 40, 60, 20]
				}
			];
			
			activity.height = 350;
			
			var config = {
				type: "line",
				data: {
					labels: [
						"Jan",
						"Feb",
						"Mar",
						"Apr",
						"May",
						"Jun",
						"Jul",
						"Aug",
						"Sep",
						"Oct",
						"Nov",
						"Dec",
					],
					datasets: [
						{
							label: "My First dataset",
							data:  [50, 75, 34, 55, 25, 70, 50, 80, 60, 90, 45, 65],
							borderColor: '#EA7A9A',
							borderWidth: "4",
							barThickness:'flex',
							backgroundColor: 'rgba(255, 199, 182, 0.3)',
							minBarLength:10
						}
					]
				},
				options: {
					responsive: true,
					maintainAspectRatio: false,
					
					legend: {
						display: false
					},
					scales: {
						yAxes: [{
							 gridColor: "navy",
							gridLines: {
								color: "rgba(0,0,0,0.1)",
								height: 50,
								drawBorder: true
							},
							ticks: {
								fontColor: "#3e4954",
								max: 100,
								min: 0,
								stepSize: 20
							},
						}],
						xAxes: [{
							barPercentage: 0.3,
							
							gridLines: {
								display: false,
								zeroLineColor: "transparent"
							},
							ticks: {
								stepSize: 20,
								fontColor: "#3e4954",
								fontFamily: "Nunito, sans-serif"
							}
						}]
					},
					elements: {
						point: {
							radius: 0,
							borderWidth: 0
						}
					},
					tooltips: {
						mode: "index",
						intersect: false,
						titleFontColor: "#888",
						bodyFontColor: "#555",
						titleFontSize: 12,
						bodyFontSize: 15,
						backgroundColor: "rgba(255,255,255,1)",
						displayColors: true,
						xPadding: 10,
						yPadding: 7,
						borderColor: "rgba(220, 220, 220, 1)",
						borderWidth: 1,
						caretSize: 6,
						caretPadding: 10
					}
				}
			};

			var ctx = document.getElementById("activityLine").getContext("2d");
			var myLine = new Chart(ctx, config);

			var items = document.querySelectorAll("#user-activity .nav-tabs .nav-item");
			items.forEach(function(item, index) {
				item.addEventListener("click", function() {
					config.data.datasets[0].data = activityData[index].first;
					myLine.update();
				});
			});
		}

	}	
	
	var chartTimeline = function(){
		
		var optionsTimeline = {
			chart: {
				type: "bar",
				height: 350,
				stacked: true,
				toolbar: {
					show: false
				},
				sparkline: {
					//enabled: true
				},
				backgroundBarRadius: 5,
				offsetX: -10,
			},
			series: [
				 {
					name: "New Clients",
					data: [70, 20, 75, 20, 50, 40, 65, 15, 40, 55, 60, 20, 75, 40, 25, 70, 20, 40, 65, 50, 35, 70, 45, 40, 35, 55, 60]
				},
				{
					name: "Retained Clients",
					data: [-60, -10, -50, -25, -30, -65, -22, -10, -50, -20, -70, -35, -60, -20, -30, -45, -70, -50, -45, -10, -20, -55, -25, -45, -35, -15, -40]
				} 
			],
			
			plotOptions: {
				bar: {
					columnWidth: "26%",
					endingShape: "rounded",
					colors: {
						backgroundBarColors: ['rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)'],
						backgroundBarOpacity: 1,
						backgroundBarRadius: 5,
						opacity:0
					},

				},
				distributed: true
			},
			colors:['#EA7A9A', '#FAC7B6'],
			
			grid: {
				show: true,
			},
			legend: {
				show: false
			},
			fill: {
				opacity: 1
			},
			dataLabels: {
				enabled: false,
				colors:['#EA7A9A', '#FAC7B6'],
				dropShadow: {
					enabled: true,
					top: 1,
					left: 1,
					blur: 1,
					opacity: 1
				}
			},
			xaxis: {
				categories: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27'],
				labels: {
					style: {
						colors: '#787878',
						fontSize: '13px',
						fontFamily: 'Poppins',
						fontWeight: 400
						
					},
				},
				crosshairs: {
					show: false,
				},
				axisBorder: {
					show: false,
				},
			},
			
			yaxis: {
				//show: false
				labels: {
					style: {
						colors: '#787878',
						fontSize: '13px',
						fontFamily: 'Poppins',
						fontWeight: 400
						
					},
				},
			},
			
			tooltip: {
				x: {
					show: true
				}
			}
    };
		var chartTimelineRender =  new ApexCharts(document.querySelector("#chartTimeline"), optionsTimeline);
		 chartTimelineRender.render();
	}
	var chartTimeline2 = function(){
		
		var optionsTimeline = {
			chart: {
				type: "bar",
				height: 350,
				stacked: true,
				toolbar: {
					show: false
				},
				sparkline: {
					//enabled: true
				},
				backgroundBarRadius: 5,
				offsetX: -10,
			},
			series: [
				 {
					name: "New Clients",
					data: [20, 20, 75, 55, 50, 40, 65, 40, 20, 55, 60, 55, 75, 40, 25, 70, 20, 40, 65, 50, 35, 70, 45, 40, 35, 55, 60]
				},
				{
					name: "Retained Clients",
					data: [-60, -10, -45, -25, -55, -65, -22, -10, -50, -10, -70, -35, -60, -20, -20, -45, -70, -50, -55, -10, -20, -55, -25, -45, -35, -15, -40]
				} 
			],
			
			plotOptions: {
				bar: {
					columnWidth: "26%",
					endingShape: "rounded",
					colors: {
						backgroundBarColors: ['rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)'],
						backgroundBarOpacity: 1,
						backgroundBarRadius: 5,
						opacity:0
					},

				},
				distributed: true
			},
			colors:['#EA7A9A', '#FAC7B6'],
			
			grid: {
				show: true,
			},
			legend: {
				show: false
			},
			fill: {
				opacity: 1
			},
			dataLabels: {
				enabled: false,
				colors:['#EA7A9A', '#FAC7B6'],
				dropShadow: {
					enabled: true,
					top: 1,
					left: 1,
					blur: 1,
					opacity: 1
				}
			},
			xaxis: {
				categories: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27'],
				labels: {
					style: {
						colors: '#787878',
						fontSize: '13px',
						fontFamily: 'Poppins',
						fontWeight: 400
						
					},
				},
				crosshairs: {
					show: false,
				},
				axisBorder: {
					show: false,
				},
			},
			
			yaxis: {
				//show: false
				labels: {
					style: {
						colors: '#787878',
						fontSize: '13px',
						fontFamily: 'Poppins',
						fontWeight: 400
						
					},
				},
			},
			
			tooltip: {
				x: {
					show: true
				}
			}
    };
		var chartTimelineRender =  new ApexCharts(document.querySelector("#chartTimeline2"), optionsTimeline);
		 chartTimelineRender.render();
	}
	
	var chartTimeline3 = function(){
		
		var optionsTimeline = {
			chart: {
				type: "bar",
				height: 350,
				stacked: true,
				toolbar: {
					show: false
				},
				sparkline: {
					//enabled: true
				},
				backgroundBarRadius: 5,
				offsetX: -10,
			},
			series: [
				 {
					name: "New Clients",
					data: [50, 10, 55, 35, 45, 30, 55, 25, 35, 42, 55, 60, 50, 45, 30, 65, 26, 35, 55, 55, 40, 60, 42, 35, 40, 60, 55]
				},
				{
					name: "Retained Clients",
					data: [-50, -20, -45, -33, -35, -55, -30, -20, -30, -10, -45, -40, -55, -25, -35, -50, -60, -55, -35, -15, -20, -55, -25, -45, -35, -15, -40]
				} 
			],
			
			plotOptions: {
				bar: {
					columnWidth: "26%",
					endingShape: "rounded",
					colors: {
						backgroundBarColors: ['rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)', 'rgba(0,0,0,0)'],
						backgroundBarOpacity: 1,
						backgroundBarRadius: 5,
						opacity:0
					},

				},
				distributed: true
			},
			colors:['#EA7A9A', '#FAC7B6'],
			
			grid: {
				show: true,
			},
			legend: {
				show: false
			},
			fill: {
				opacity: 1
			},
			dataLabels: {
				enabled: false,
				colors:['#EA7A9A', '#FAC7B6'],
				dropShadow: {
					enabled: true,
					top: 1,
					left: 1,
					blur: 1,
					opacity: 1
				}
			},
			xaxis: {
				categories: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27'],
				labels: {
					style: {
						colors: '#787878',
						fontSize: '13px',
						fontFamily: 'Poppins',
						fontWeight: 400
						
					},
				},
				crosshairs: {
					show: false,
				},
				axisBorder: {
					show: false,
				},
			},
			
			yaxis: {
				//show: false
				labels: {
					style: {
						colors: '#787878',
						fontSize: '13px',
						fontFamily: 'Poppins',
						fontWeight: 400
						
					},
				},
			},
			
			tooltip: {
				x: {
					show: true
				}
			}
    };
		var chartTimelineRender =  new ApexCharts(document.querySelector("#chartTimeline3"), optionsTimeline);
		 chartTimelineRender.render();
	}
	var donutChart2 = function(){
		$("span.donut2").peity("donut", {
			width: "80",
			height: "80"
		});
	}
	
	var chartBar2 = function(){
		if(jQuery('#chart_widget_2').length > 0 ){
	
			const chart_widget_2 = document.getElementById("chart_widget_2").getContext('2d');
			//generate gradient
			const chart_widget_2gradientStroke = chart_widget_2.createLinearGradient(250, 0, 0, 0);
			chart_widget_2gradientStroke.addColorStop(1, "#EA7A9A");
			chart_widget_2gradientStroke.addColorStop(0, "#FAC7B6");

			// chart_widget_2.attr('height', '100');

			new Chart(chart_widget_2, {
				type: 'bar',
				data: {
					defaultFontFamily: 'Poppins',
					labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
					datasets: [
						{
							label: "My First dataset",
							data: [15, 40, 55, 40, 25, 35, 40, 50, 85, 95, 54, 35],
							borderColor: chart_widget_2gradientStroke,
							borderWidth: "0",
							backgroundColor: chart_widget_2gradientStroke, 
							hoverBackgroundColor: chart_widget_2gradientStroke
						}
					]
				},
				options: {
					legend: false,
					responsive: true, 
					maintainAspectRatio: false,  
					scales: {
						yAxes: [{
							display: false, 
							ticks: {
								beginAtZero: true, 
								display: false, 
								max: 100, 
								min: 0, 
								stepSize: 10
							}, 
							gridLines: {
								display: false, 
								drawBorder: false
							}
						}],
						xAxes: [{
							display: false, 
							barPercentage: 0.4, 
							gridLines: {
								display: false, 
								drawBorder: false
							}, 
							ticks: {
								display: false
							}
						}]
					}
				}
			});

		}
		
		
	}
	
	/* Function ============ */
		return {
			init:function(){
			},
			
			
			load:function(){
				donutChart1();
				radialBar();
				radialBar2();
				radialBar3();
				chartBar();
				chartTimeline();
				chartTimeline2();
				chartTimeline3();
				donutChart2();
				chartBar2();
			},
			
			resize:function(){
				
			}
		}
	
	}();

	jQuery(document).ready(function(){
	});
		
	jQuery(window).on('load',function(){
		setTimeout(function(){
			dzChartlist.load();
		}, 1000); 
		
	});

	jQuery(window).on('resize',function(){
		
		
	});     

})(jQuery);