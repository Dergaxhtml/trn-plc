reg-event-db
:load-views
[validate-spec update-storage]
(fn [db [_ views]]
    (update db views #(merge % views))))