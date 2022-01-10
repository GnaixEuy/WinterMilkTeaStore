(function($) {
    /* "use strict" */


 var dzChartlist = function(){
	let draw = Chart.controllers.line.__super__.draw; //draw shadow
	var screenWidth = $(window).width();
	var donutChart = function(){
		$("span.donut").peity("donut", {
			width: "70",
			height: "70"
		});
	}
	var chartBar = function(){
		
		var options = {
			  series: [
				{
					name: 'Net Profit',
					data: [50, 70, 40, 80, 30, 60, 100],
					//radius: 12,	
				}, 				
			],
				chart: {
				type: 'area',
				height: 350,
				toolbar: {
					show: false,
				},
				
			},
			plotOptions: {
			  bar: {
				horizontal: false,
				columnWidth: '55%',
				endingShape: 'rounded'
			  },
			},
			colors:['#EA7A9A'],
			dataLabels: {
			  enabled: false,
			},
			markers: {
		shape: "circle",
		},
		
		
			legend: {
				show: false,
			},
			stroke: {
			  show: true,
			  width: 4,
			  colors:['#EA7A9A'],
			},
			
			grid: {
				borderColor: '#eee',
			},
			xaxis: {
				
			  categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'July'],
			  labels: {
				style: {
					colors: '#3e4954',
					fontSize: '13px',
					fontFamily: 'Poppins',
					fontWeight: 100,
					cssClass: 'apexcharts-xaxis-label',
				},
			  },
			  crosshairs: {
			  show: false,
			  }
			},
			yaxis: {
				labels: {
			   style: {
				  colors: '#3e4954',
				  fontSize: '13px',
				   fontFamily: 'Poppins',
				  fontWeight: 100,
				  cssClass: 'apexcharts-xaxis-label',
			  },
			  },
			},
			fill: {
			  opacity: 1,
			  colors:'#FAC7B6'
			},
			tooltip: {
			  y: {
				formatter: function (val) {
				  return "$ " + val + " thousands"
				}
			  }
			}
			};

			var chartBar1 = new ApexCharts(document.querySelector("#chartBar"), options);
			chartBar1.render();
	}
	
	var counterBar = function(){
		$(".counter").counterUp({
			delay: 30,
			time: 3000
		});
	}
	/* Function ============ */
		return {
			init:function(){
			},
			
			
			load:function(){
				donutChart();
				chartBar();
				counterBar();
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