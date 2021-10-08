// document.addEventListener('DOMContentLoaded', function() {
//   var calendarEl = document.getElementById('calendar');
//
//   var calendar = new FullCalendar.Calendar(calendarEl, {
//     headerToolbar: {
//       left: 'prev,next today',
//       center: 'title',
//       right: 'dayGridMonth,timeGridWeek,timeGridDay'
//     },
//     initialDate: '2021-06-20',
//     navLinks: true, // can click day/week names to navigate views
//     selectable: true,
//     selectMirror: true,
//     locale:"zh-cn",
//     select: function(arg) {
//       var title = prompt('Event Title:');
//       if (title) {
//         calendar.addEvent({
//           title: title,
//           start: arg.start,
//           end: arg.end,
//           allDay: arg.allDay
//         })
//       }
//       calendar.unselect()
//     },
//     eventClick: function(arg) {
//       if (confirm('Are you sure you want to delete this event?')) {
//         arg.event.remove()
//       }
//     },
//     editable: true,
//     dayMaxEvents: true, // allow "more" link when too many events
//     events: [
//       {
//         title: 'All Day Event',
//         start: '2020-09-01'
//       },
//       {
//         title: 'Long Event',
//         start: '2020-09-07',
//         end: '2020-09-10'
//       },
//       {
//         groupId: 999,
//         title: 'Repeating Event',
//         start: '2020-09-09T16:00:00'
//       },
//       {
//         groupId: 999,
//         title: 'Repeating Event',
//         start: '2020-09-16T16:00:00'
//       },
//       {
//         title: 'Conference',
//         start: '2020-09-11',
//         end: '2020-09-13'
//       },
//       {
//         title: 'Meeting',
//         start: '2020-09-12T10:30:00',
//         end: '2020-09-12T12:30:00'
//       },
//       {
//         title: 'Lunch',
//         start: '2020-09-12T12:00:00'
//       },
//       {
//         title: 'Meeting',
//         start: '2020-09-12T14:30:00'
//       },
//       {
//         title: 'Happy Hour',
//         start: '2020-09-12T17:30:00'
//       },
//       {
//         title: 'Dinner',
//         start: '2020-09-12T20:00:00'
//       },
//       {
//         title: 'Birthday Party',
//         start: '2020-09-13T07:00:00'
//       },
//       {
//         title: 'Click for Google',
//         url: 'http://google.com/',
//         start: '2020-09-28'
//       }
//     ]
//   });
//   //   events:function(start, end, timezone, callback){//初始化
//   //     var events = [];
//   //     $.ajax({
//   //       type:"GET",
//   //       URL:"/allevents",
//   //       dataType:'json',
//   //       data:{},
//   //       success:function(rel){
//   //         var val = {}
//   //         $.each(rel.data,function(i,data) {
//   //           val={
//   //             "title":rel.data[i].sch_name,
//   //             "start":rel.data[i].start,
//   //             "end":rel.data[i].end,
//   //             // "id":rel.data[i].id
//   //           }
//   //           events.push(val);
//   //         });
//   //         console.log(events)
//   //         events.push(events);
//   //         callback(events)
//   //       }
//   //     });
//   //
//   //   },
//   // });
//   calendar.render();
// });

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialDate: '2021-06-21',
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectMirror: true,
        locale:"zh-cn",
        select: function(arg) {
            var title = prompt('Event Title:');
            if (title) {
                calendar.addEvent({
                    title: title,
                    start: arg.start,
                    end: arg.end,
                    allDay: arg.allDay
                })
            }
            calendar.unselect()
        },
        eventClick: function(arg) {
            if (confirm('Are you sure you want to delete this event?')) {
                arg.event.remove()
            }
        },
        editable: true,
        dayMaxEvents: true, // allow "more" link when too many events
        events: {}

    });

    calendar.render();
});